package bo.ucb.edu.ingsoft.api;

import bo.ucb.edu.ingsoft.bl.SaleBl;
import bo.ucb.edu.ingsoft.bl.TransactionBl;
import bo.ucb.edu.ingsoft.dto.CompanyDto;
import bo.ucb.edu.ingsoft.dto.SaleDto;
import bo.ucb.edu.ingsoft.model.Transaction;
import bo.ucb.edu.ingsoft.util.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/sale")
public class SaleApi {

    private SaleBl saleBl;
    private TransactionBl transactionBl;

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleApi.class);

    @Autowired
    public SaleApi(TransactionBl transactionBl, SaleBl saleBl){
        this.transactionBl = transactionBl;
        this.saleBl = saleBl;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public SaleDto createSale(@RequestBody SaleDto saleDto, HttpServletRequest request) {
        // Creamos transaccion para la operación.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        SaleDto saleResponse = saleBl.createSale(saleDto, transaction);
        return saleResponse;
    }


}
