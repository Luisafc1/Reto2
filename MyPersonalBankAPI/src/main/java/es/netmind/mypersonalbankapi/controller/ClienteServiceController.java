package es.netmind.mypersonalbankapi.controller;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.persistencia.ClienteDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/clients")
@Validated
public class ClienteServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceController.class);

    @Autowired
    private ClienteDataRepo repo;

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Empresa> save(@RequestBody @Valid Empresa newCliente) {
        logger.info("newCliente:" + newCliente);
        newCliente.setId(null);
        return new ResponseEntity<>(repo.save(newCliente), HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{pid}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Cliente> getOne(@PathVariable("pid") @Min(1) int id) {
        return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);
    }

}
