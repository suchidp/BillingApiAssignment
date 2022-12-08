
package com.billingapi.controller;
import java.math.BigDecimal;
import com.billingapi.controller.request.InvoiceRequest;
import com.billingapi.controller.request.InvoiceResponse;
import com.billingapi.exception.InvoiceNotFoundException;
import com.billingapi.model.Invoice;
import com.billingapi.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
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



@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @Autowired
    private ObjectMapper objectMapper;
    private Object BigDecimal;



 /*  Test to Create invoice if Method is not allowed then it throws exception  HttpRequestMethodNotSupported
  */
    @Test
    public void should_throw_exception_whenCreateInvoice_ifHttpRequestMethodNotSupported() throws Exception {

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

        response.andDo(print()).
                andExpect(status().isMethodNotAllowed());

    }


    /*  Test to Create invoice if Method arguments is not valid   then it throws exception  MethodArgumentTypeMismatch
     */
    @Test
    public void should_throw_exception_whenCreateInvoice_ifMethodArgumentTypeMismatch() throws Exception {

        // given - precondition or setup
                  int invoiceId = 1;
                  Invoice newInvoice = Invoice.builder()
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
                .total(new BigDecimal("98000.0"))
                .build();

        given(invoiceService.findInvoiceById(invoiceId)).willReturn(newInvoice);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/invoice/aa", invoiceId));

        // then - verify the output
        response.andExpect(status().isBadRequest())
                .andDo(print());
    }




  /*  Test to Create invoice using InvoiceRequest a request Dto which giving the request to add new Invoice
     and return  InvoiceResponse
  */
       @Test
       public void givenInvoiceObject_whenCreateInvoice_thenReturnaddInvoice() throws Exception {
                    Invoice newInvoice = Invoice.builder()
                   .invoiceId(1)
                   .itemName("jeans")
                   .price(new BigDecimal("98000.00"))
                   .tax(10.00)
                   .vat(15.00)
                   .discount(10.00)
                   .isItemOnSale(true)
                   .discountOnSale(10.00)
                   .userId(1)
                   .isDeleted(false)
                   .description("good")
                   .quantity(2)
                   .total(new BigDecimal(205800.00))
                   .build();





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
           BigDecimal newtax = new BigDecimal(tax);
           BigDecimal newvat = new BigDecimal(vat);
           BigDecimal newdiscount = new BigDecimal(discount);
           BigDecimal newdiscountonsale = new BigDecimal(discountOnSale);
           BigDecimal newquantity = new BigDecimal(quantity);
           BigDecimal totalDiscount = ((newtax).add(newvat).subtract(newdiscount).subtract(newdiscountonsale));
           BigDecimal subTotal = (price.multiply(totalDiscount).divide(new BigDecimal("100.0")));
           BigDecimal total = (price.add(subTotal));
           BigDecimal alltotal = total.multiply(newquantity);
           invoiceRequest.setTotal(alltotal);


           InvoiceResponse invoiceResponse = new InvoiceResponse();
           invoiceResponse.setItemName(invoiceRequest.getItemName());
           invoiceResponse.setPrice(invoiceRequest.getPrice());
           invoiceResponse.setTax(invoiceRequest.getTax());
           invoiceResponse.setVat(invoiceRequest.getVat());
           invoiceResponse.setDiscount(invoiceRequest.getDiscount());
           invoiceResponse.setItemOnSale(invoiceRequest.isItemOnSale());
           invoiceResponse.setDiscountOnSale(invoiceRequest.getDiscountOnSale());
           invoiceResponse.setDescription(invoiceRequest.getDescription());
           invoiceResponse.setQuantity(invoiceRequest.getQuantity());
           invoiceResponse.setTotal(invoiceRequest.getTotal());

           // then - verify the result or output using assert statements
           when(invoiceService.save(any())).thenReturn(newInvoice);
           this.mockMvc.perform(post("/invoice")
                           .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(invoiceResponse)))
                   .andExpect(status().isCreated())
                .andDo(print())
               .andExpect(jsonPath("$.itemName", is(invoiceResponse.getItemName())))
               .andExpect(jsonPath("$.price", is(invoiceResponse.getPrice().doubleValue())))
                .andExpect(jsonPath("$.tax", is(invoiceResponse.getTax())))
                .andExpect(jsonPath("$.vat", is(invoiceResponse.getVat())))
                .andExpect(jsonPath("$.discount", is(invoiceResponse.getDiscount())))
                .andExpect(jsonPath("$.itemOnSale", is(invoiceResponse.isItemOnSale())))
                .andExpect(jsonPath("$.discountOnSale", is(invoiceResponse.getDiscountOnSale())))
                .andExpect(jsonPath("$.description", is(invoiceResponse.getDescription())))
                .andExpect(jsonPath("$.quantity", is(invoiceResponse.getQuantity())))
               .andExpect(jsonPath("$.total", is(invoiceResponse.getTotal().intValue())))
        ;
    }



    /* Negative scenario Test to Create invoice if  Price Is Greater Than 99999999   then it throws exception  MethodArgumentNotValidException
     */
    @Test
    public void should_throw_exception_whenCreateInvoice_ifPriceIsGreaterThan99999999_thenReturnaddInvoiceFailed() throws Exception {
        InvoiceRequest newInvoice = InvoiceRequest.builder()


                .itemName("jeans")
                .price(new BigDecimal(2058000000000.00))
                .tax(10.00)
                .vat(15.00)
                .discount(20.00)
                .isItemOnSale(true)
                .discountOnSale(10.00)
                .quantity(5)
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
    /* Negative scenario Test to Create invoice if ItemName Is Invalid  then it throws exception  MethodArgumentNotValidException
*/
   @Test
   public void should_throw_exception_whenCreateInvoice_ifItemNameIsInvalid_thenReturnaddInvoiceFailed() throws Exception {
       InvoiceRequest newInvoice = InvoiceRequest.builder()
               .itemName("jeans#")
               .price(new BigDecimal(205800.00))
               .tax(10.00)
               .vat(15.00)
               .discount(20.00)
               .isItemOnSale(true)
               .discountOnSale(10.00)
               .quantity(5)
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

    /* Negative scenario Test to Create invoice if Tax Is Greater Than 99 then it throws exception  MethodArgumentNotValidException
     */
   @Test
   public void should_throw_exception_whenCreateInvoice_ifTaxIsGreaterThan99_thenReturnaddInvoiceFailed() throws Exception {
       InvoiceRequest newInvoice = InvoiceRequest.builder()
               .itemName("jeans")
               .price(new BigDecimal("98000.0"))
               .tax(100.00)
               .vat(15.00)
               .discount(20.00)
               .isItemOnSale(true)
               .discountOnSale(10.00)
               .quantity(5)
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
    /* Negative scenario Test to Create invoice if Vat Is Greater Than 99 then it throws exception  MethodArgumentNotValidException
     */
   @Test
   public void should_throw_exception_whenCreateInvoice_ifvatIsGreaterThan99_thenReturnaddInvoiceFailed() throws Exception {
       InvoiceRequest newInvoice = InvoiceRequest.builder()
               .itemName("jeans")
               .price(new BigDecimal("98000.0"))
               .tax(10.00)
               .vat(150.00)
               .discount(20.00)
               .isItemOnSale(true)
               .discountOnSale(10.00)
               .quantity(5)
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


    /* Negative scenario Test to Create invoice if Discount  Is Greater Than 99 then it throws exception  MethodArgumentNotValidException
     */
   @Test
   public void should_throw_exception_whenCreateInvoice_ifDiscountIsGreaterThan99_thenReturnaddInvoiceFailed() throws Exception {
       InvoiceRequest newInvoice = InvoiceRequest.builder()
               .itemName("jeans")
               .price(new BigDecimal("98000.0"))
               .tax(10.00)
               .vat(15.00)
               .discount(100.00)
               .isItemOnSale(true)
               .discountOnSale(10.00)
               .quantity(5)
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

    /* Negative scenario Test to Create invoice if DiscountOnSale Is Greater Than 99 then it throws exception  MethodArgumentNotValidException
     */
   @Test
   public void should_throw_exception_whenCreateInvoice_ifDiscountOnSaleIsGreaterThan99_thenReturnaddInvoiceFailed() throws Exception {
       InvoiceRequest newInvoice = InvoiceRequest.builder()
               .itemName("jeans")
               .price(new BigDecimal("98000.0"))
               .tax(10.00)
               .vat(15.00)
               .discount(10.00)
               .isItemOnSale(true)
               .discountOnSale(100.00)
               .quantity(5)
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

    /* Negative scenario Test to Create invoice if Quantity Is Greater Than 99999999 then it throws exception  MethodArgumentNotValidException
     */
   @Test
   public void should_throw_exception_whenCreateInvoice_ifQuantityIsGreaterThan99999999_thenReturnaddInvoiceFailed() throws Exception {
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
       ResultActions response = mockMvc.perform(post("/invoice")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(newInvoice)));

       // then - verify the result or output using assert statements
       response.andDo(print()).
               andExpect(status().isBadRequest());

   }
    /* Negative scenario Test to Create invoice if Price is zero then it throws exception  MethodArgumentNotValidException
     */

   @Test
   public void should_throw_exception_whenCreateInvoice_ifPriceIsZero_thenReturnaddInvoiceFailed() throws Exception {
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
       ResultActions response = mockMvc.perform(post("/invoice")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(newInvoice)));

       // then - verify the result or output using assert statements
       response.andDo(print()).
               andExpect(status().isBadRequest());

   }



    /*  Test to find invoice  by Id using findInvoiceById
    it return Invoice of  particular Id
     */


   @Test
   public void givenInvoice_whenfindInvoiceById_thenReturnfindById() throws Exception {
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



   /* Test To find InvoiceById
    * @param  invoiceId an Id giving the Invoice of specific Id
    * @return      Invoice of specific Id.
    * @throws InvoiceNotFoundException  If an Invoice of specific Id is not found
    */
    @Test
    public void givenInvalidInvoiceId_whenfindInvoiceById_thenReturnEmpty() throws Exception {
        int invoiceId = 1;
        Invoice newInvoice = Invoice.builder()
                .invoiceId(1)
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




    /* Test To find  List of Invoices
     */

    @Test
    public void givenListOfInvoices_whenGetAllInvoice_thenReturnInvoiceList() throws Exception {
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
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfInvoice.size())));

    }




}
