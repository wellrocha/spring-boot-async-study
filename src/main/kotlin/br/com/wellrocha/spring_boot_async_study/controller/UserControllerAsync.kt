package br.com.wellrocha.spring_boot_async_study.controller

import br.com.wellrocha.spring_boot_async_study.adapter.async.GetAddressesApiAdapterAsync
import br.com.wellrocha.spring_boot_async_study.adapter.async.GetNamesApiAdapterAsync
import br.com.wellrocha.spring_boot_async_study.adapter.async.GetNamesApiAdapterThrowExceptionAsync
import br.com.wellrocha.spring_boot_async_study.adapter.async.GetPhonesApiAdapterAsync
import br.com.wellrocha.spring_boot_async_study.dto.UserData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("users")
class UserControllerAsync (
    private val getNamesApiAdapterAsync: GetNamesApiAdapterAsync,
    private val getAddressesApiAdapterAsync: GetAddressesApiAdapterAsync,
    private val getPhonesApiAdapterAsync: GetPhonesApiAdapterAsync,
    private val getNamesApiAdapterThrowExceptionAsync: GetNamesApiAdapterThrowExceptionAsync
){
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserControllerAsync::class.java)
    }

    @GetMapping("/{name}/async")
    fun getUserDataAsync(
        @PathVariable("name", required = true) name: String
    ): Any {
        val names = getNamesApiAdapterAsync.execute()
        val addresses = getAddressesApiAdapterAsync.execute()
        val phones = getPhonesApiAdapterAsync.execute()

        CompletableFuture.allOf(names, addresses, phones).join()

        return names.get()?.find {
            it.name.contains(name, ignoreCase = true)
        }?.let { name ->
            return UserData(
                name = name.name,
                phone = phones.get()?.find {
                    it.phoneId == name.phoneId
                }?.phone ?: "Phone Not Found",
                address = addresses.get()?.find {
                    it.addressId == name.addressId
                }?.address ?: "Address Not Found"
            )
        } ?: ResponseEntity
            .status(404)
            .body("User Data Not Found")
    }

    @GetMapping("/{name}/async/exception")
    fun getUserDataAsyncException(
        @PathVariable("name", required = true) name: String
    ): Any = try {
        val names = getNamesApiAdapterThrowExceptionAsync.execute()
        UserData(
            name = names.get()?.find {
                it.name.contains(name, ignoreCase = true)
            }?.name ?: "",
            phone = "",
            address = ""
        )
    } catch (exception: Exception) {
        ResponseEntity
            .status(500)
            .body(exception.message)
    }
}