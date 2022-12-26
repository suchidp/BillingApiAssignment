
package com.billingapi.controller;

import java.math.BigDecimal;

import com.billingapi.controller.request.InvoiceRequest;
import com.billingapi.exception.InvoiceNotFoundException;
import com.billingapi.model.Invoice;
import com.billingapi.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @Autowired
    private ObjectMapper objectMapper;
    private String INVALID_INVOICE_ID_URL;



    Invoice invoice = Invoice.builder()
            .itemName("jeans")
            .price(new BigDecimal("98000.0"))
            .tax(10.00)
            .vat(15.00)
            .discount(10.00)
            .isItemOnSale(true)
            .discountOnSale(10.00)
            .userId(1)
            .isDeleted(false)
            .timestamp(LocalDateTime.now())
            .description("good")
            .quantity(2)
            .total(new BigDecimal("205800.0"))
            .build();






    /**
     * JUnit test  which throws  HttpRequestMethodNotSupported exception
     * Negative  scenario to test if method type is invalid
     * @return the result or output using assert statements.
     */
    @Test
    public void testShould_throw_exception_whenCreateInvoice_ifHttpRequestMethodNotSupported() throws Exception {

        InvoiceRequest newInvoice = InvoiceRequest.builder()
                .itemName("jeans")
                .price(new BigDecimal("98000.0"))
                .tax(10.00)
                .vat(15.00)
                .discount(10.00)
                .isItemOnSale(true)
                .discountOnSale(10.00)
                .quantity(999999990)
                .total(new BigDecimal("98000.0"))
                .description("good")
                .build();

        given(invoiceService.save(any(Invoice.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newInvoice)));
        // then - verify the result or output using assert statements
        response.andExpect(status().isMethodNotAllowed());

    }

    /**
     * JUnit test  which throws  MethodArgumentTypeMismatch exception
     * Negative  scenario to test if method argument type is mismatch by using  INVALID_INVOICE_ID_URL
     * @return the result or output using assert statements.
     */
    @Test
    public void testShould_throw_exception_whenCreateInvoice_ifMethodArgumentTypeMismatch() throws Exception {

        // given - precondition or setup
        int invoiceId = 1;
        given(invoiceService.findInvoiceById(invoiceId)).willReturn(invoice);
        INVALID_INVOICE_ID_URL = "http://localhost:8080/invoice/aaa";
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get(INVALID_INVOICE_ID_URL, invoiceId));

        // then - verify the output
        response.andExpect(status().isBadRequest())
        ;
    }


    /**
     * JUnit test to Create new Invoice  which throws exception
     * Positive scenario Test to Create new Invoice
     * InvoiceRequest a request Dto which give the request  to add new Invoice
     * @return the result or output using assert statements.
     */

    @Test
    public void testGivenInvoiceObject_whenCreateInvoice_thenReturnaddInvoice() throws Exception {
        InvoiceRequest invoiceRequest = InvoiceRequest.builder()
                .itemName("jeans")
                .price(new BigDecimal("98000.0"))
                .tax(10.00)
                .vat(15.00)
                .discount(10.00)
                .isItemOnSale(true)
                .discountOnSale(10.00)
                .quantity(2)
                .description("good")
                .build();

        BigDecimal price = invoiceRequest.getPrice();
        double tax = invoiceRequest.getTax();
        double vat = invoiceRequest.getVat();
        double discount = invoiceRequest.getDiscount();
        double discountOnSale = invoiceRequest.getDiscountOnSale();
        int quantity = invoiceRequest.getQuantity();

        BigDecimal totalDiscount = (new BigDecimal(tax).add(new BigDecimal(vat)).subtract(new BigDecimal(discount)).subtract(new BigDecimal(discountOnSale)));
        BigDecimal subTotal = (price.multiply(totalDiscount).divide(new BigDecimal("100.0")));
        BigDecimal total = (price.add(subTotal));
        BigDecimal alltotal = total.multiply(new BigDecimal(quantity));
        invoiceRequest.setTotal(alltotal);

        // then - verify the result or output using assert statements
        when(invoiceService.save(any())).thenReturn(invoice);
        this.mockMvc.perform(post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(invoiceRequest)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.itemName", is(invoiceRequest.getItemName())))
                .andExpect(jsonPath("$.price", is(invoiceRequest.getPrice().doubleValue())))
                .andExpect(jsonPath("$.tax", is(invoiceRequest.getTax())))
                .andExpect(jsonPath("$.vat", is(invoiceRequest.getVat())))
                .andExpect(jsonPath("$.discount", is(invoiceRequest.getDiscount())))
                .andExpect(jsonPath("$.itemOnSale", is(invoiceRequest.isItemOnSale())))
                .andExpect(jsonPath("$.discountOnSale", is(invoiceRequest.getDiscountOnSale())))
                .andExpect(jsonPath("$.description", is(invoiceRequest.getDescription())))
                .andExpect(jsonPath("$.quantity", is(invoiceRequest.getQuantity())))
                .andExpect(jsonPath("$.total", is(invoiceRequest.getTotal().doubleValue())))
        ;
    }



    /**
     * Negative scenario to test Invoice using @ParameterizedTest  and @MethodSource Annotations
     * JUnit test to Create new Invoice  which throws exception
     * InvoiceRequest a request Dto which give the request   by passing different arguments such as itemName,price,tax,vat,discount,discountOnSale,quantity
     * then it throws  MethodArgumentNotValidException.
     * return    the result or output using assert statements.
     */


    @ParameterizedTest
    @MethodSource("invoice")
    public void testShould_throw_exception_whenCreateInvoice_thenReturnaddInvoiceFailed(String itemName, BigDecimal price, double tax, double vat, double discount, double discountOnSale, int quantity) throws Exception {
        InvoiceRequest newInvoice = InvoiceRequest.builder()
                .itemName(itemName)
                .price(price)
                .tax(tax)
                .vat(vat)
                .discount(discount)
                .isItemOnSale(true)
                .discountOnSale(discountOnSale)
                .quantity(quantity)
                .description("good")
                .build();
        given(invoiceService.save(any(Invoice.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newInvoice)));
        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isBadRequest());


    }
    public static Stream<Arguments> invoice() {
        return Stream.of(
                arguments("jeans/", new BigDecimal("9800000000000.00"), 100.00, 600.00, 120.00, 400.00, 0),
                arguments("jeans*", new BigDecimal("99999999.999"), 10.999, 10.999, 11.999, 11.999, 988000000),
                arguments("jeans+", new BigDecimal("0.0"), 100.00, 600.00, 120.00, 400.00, 988000000),
                arguments("ea", new BigDecimal("0.0001"), 100.00, 600.00, 120.00, 400.00, 988000000),
                arguments("JEANSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS", new BigDecimal("9200000000000.0"), 99.9999, 99.9999, 99.99999, 99.9999, 988000000))
                ;
    }


    /**
     * Positive scenario to test Invoice using @ParameterizedTest  and @MethodSource Annotations
     * JUnit test to Create new Invoice  which throws exception
     * InvoiceRequest a request Dto which give the request   by passing different arguments such as itemName,price,tax,vat,discount,discountOnSale,quantity
     * return    the result or output using assert statements.
     */
    @ParameterizedTest
    @MethodSource("invoices")
    public void testCreateInvoice_thenReturnaddInvoiceFailed(String itemName, BigDecimal price, double tax, double vat, double discount, double discountOnSale, int quantity) throws Exception {
        InvoiceRequest newInvoice = InvoiceRequest.builder()
                .itemName(itemName)
                .price(price)
                .tax(tax)
                .vat(vat)
                .discount(discount)
                .isItemOnSale(true)
                .discountOnSale(discountOnSale)
                .quantity(quantity)
                .description("good")
                .build();
        given(invoiceService.save(any(Invoice.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newInvoice)));
        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated());
    }

    public static Stream<Arguments> invoices() {
        return Stream.of(

                arguments("jeans", new BigDecimal("980000.00"), 20.00, 30.00, 10.00, 40.00, 23))

                ;


    }


    /*
     * JUnit   Test to  find Invoice By Id.
     * @return  This returns Invoice of  particular Id
     */
    @Test
    public void testGivenInvalidInvoiceId_whenfindInvoiceById_thenReturnInvoice()throws Exception {
        // given - precondition or setup
        int invoiceId = 1;
        Invoice newInvoice = Invoice.builder()
                .itemName("jeans")
                .price(new BigDecimal("98000.00"))
                .tax(10.00)
                .vat(15.00)
                .discount(10.00)
                .isItemOnSale(true)
                .discountOnSale(10.00)
                .userId(1)
                .isDeleted(false)
                .timestamp(LocalDateTime.now())
                .description("good")
                .quantity(2)
                .total(new BigDecimal(205800.0))

                .build();

        given(invoiceService.findInvoiceById(invoiceId)).willReturn(newInvoice);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/invoice/{invoiceId}", invoiceId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.invoiceId", is(newInvoice.getInvoiceId())))
                .andExpect(jsonPath("$.itemName", is(newInvoice.getItemName())))
                .andExpect(jsonPath("$.price", is(newInvoice.getPrice().doubleValue())))
                .andExpect(jsonPath("$.tax", is(newInvoice.getTax())))
                .andExpect(jsonPath("$.vat", is(newInvoice.getVat())))
                .andExpect(jsonPath("$.discount", is(newInvoice.getDiscount())))
                .andExpect(jsonPath("$.itemOnSale", is(newInvoice.isItemOnSale())))
                .andExpect(jsonPath("$.discountOnSale", is(newInvoice.getDiscountOnSale())))
                .andExpect(jsonPath("$.userId", is(newInvoice.getUserId())))
                .andExpect(jsonPath("$.description", is(newInvoice.getDescription())))
                .andExpect(jsonPath("$.quantity", is(newInvoice.getQuantity())))
                .andExpect(jsonPath("$.total", is(newInvoice.getTotal().intValue())));


    }


    /* JUnit test  To find InvoiceById
     * @param  invoiceId an Id giving the Invoice of specific Id
     * @return      Invoice of specific Id.
     * @throws InvoiceNotFoundException  If an Invoice of specific Id is not found
     */
    @Test
    public void  testGivenInvalidInvoiceId_whenfindInvoiceById_thenInvoiceNotFound() throws Exception {
        int invoiceId = 1;
        Invoice newInvoice = Invoice.builder()
                .itemName("jeans")
                .price(new BigDecimal(205800.00))
                .tax(10.00)
                .vat(15.00)
                .discount(10.00)
                .isItemOnSale(true)
                .discountOnSale(10.00)
                .userId(1)
                .isDeleted(false)
                .timestamp(LocalDateTime.now())
                .description("good")
                .quantity(2)
                .total(new BigDecimal(205800.00))
                .build();

        Mockito.doThrow(new InvoiceNotFoundException(newInvoice.getItemName())).when(invoiceService).findInvoiceById(newInvoice.getInvoiceId());
        ResultActions response = mockMvc.perform(get("/invoice/{invoiceId}", invoiceId));
        response.andExpect(status().isNotFound())
                .andDo(print());
    }


    /**
     * JUnit test To get list of all Invoices
     * created arraylist of Invoices and add invoice into it
     * @return list of all Invoices ,result or output using assert statements .
     */
    @Test
    public void testGetInvoicesShouldReturnListOfInvoices() throws Exception {
        // given - precondition or setup
        List<Invoice> listOfInvoice = new ArrayList<>();
        listOfInvoice.add(Invoice.builder()
                .invoiceId(1)
                .itemName("jeans")
                .price(new BigDecimal("98000.0"))
                .tax(10.00)
                .vat(15.00)
                .discount(10.00)
                .isItemOnSale(true)
                .discountOnSale(10.00)
                .userId(1)
                .isDeleted(false)
                .timestamp(LocalDateTime.now())
                .description("good")
                .quantity(999999990)
                .total(new BigDecimal("98000.0"))
                .build());

        listOfInvoice.add(Invoice.builder()
                .invoiceId(2)
                .itemName("MOBILE")
                .price(new BigDecimal("98000.0"))
                .tax(10.00)
                .vat(15.00)
                .discount(10.00)
                .isItemOnSale(true)
                .discountOnSale(10.00)
                .userId(1)
                .isDeleted(false)
                .timestamp(LocalDateTime.now())
                .description("good")
                .quantity(999999990)
                .total(new BigDecimal("98000.0"))
                .build());
        given(invoiceService.getAllInvoice()).willReturn(listOfInvoice);
        ResultActions response = mockMvc.perform(get("/invoice"));
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        is(listOfInvoice.size())));

    }


}

