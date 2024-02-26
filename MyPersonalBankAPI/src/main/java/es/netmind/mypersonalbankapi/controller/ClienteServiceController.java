package es.netmind.mypersonalbankapi.controller;

import es.netmind.mypersonalbankapi.modelos.StatusMessage;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.persistencia.ClienteDataRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "My Personal Bank API", description = "Clients management APIs")
public class ClienteServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceController.class);

    @Autowired
    private ClienteDataRepo repo;

    @Operation(summary = "Create a client ", description = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "412", description = "Not created- incorrect data")
    })
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Empresa> save(@RequestBody @Valid Empresa newCliente) {
        logger.info("newCliente:" + newCliente);
        newCliente.setId(null);
        return new ResponseEntity<>(repo.save(newCliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Get a list of clients", description = "Returns a list of clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Cliente>> getAll() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get a client by id", description = "Returns a client as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The client was not found")

    })
    @GetMapping(value = "/{cid}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Cliente> getOne(@PathVariable("cid") @Min(1) int id) {
        return new ResponseEntity<>(repo.findById(id).get(), HttpStatus.OK);
    }

    @Operation(summary = "Modificated a client ", description = "Modificated a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully modificated"),
            @ApiResponse(responseCode = "412", description = "Not modificated- incorrect data")
    })
    @PutMapping(value = "/{cid}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> save(@PathVariable("cid") @Min(1) int id, @RequestBody @Valid Empresa modCliente) {
        if (id == modCliente.getId()) {
            return new ResponseEntity<>(repo.save(modCliente), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), "Id y client.id deben cohincidir"), HttpStatus.PRECONDITION_FAILED);
        }
    }

}
