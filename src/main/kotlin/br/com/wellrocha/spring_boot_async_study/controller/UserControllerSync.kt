package br.com.wellrocha.spring_boot_async_study.controller

import br.com.wellrocha.spring_boot_async_study.adapter.sync.GetAddressesApiAdapterSync
import br.com.wellrocha.spring_boot_async_study.adapter.sync.GetNamesApiAdapterSync
import br.com.wellrocha.spring_boot_async_study.adapter.sync.GetPhonesApiAdapterSync
import br.com.wellrocha.spring_boot_async_study.dto.UserData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("users")
class UserControllerSync (
    private val getNamesApiAdapterSync: GetNamesApiAdapterSync,
    private val getAddressesApiAdapterSync: GetAddressesApiAdapterSync,
    private val getPhonesApiAdapterSync: GetPhonesApiAdapterSync
){
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserControllerSync::class.java)
    }

    @GetMapping("/{name}/sync")
    fun getUserDataSync(
        @PathVariable("name", required = true) name: String
    ): Any {
        val names = getNamesApiAdapterSync.execute()
        val addresses = getAddressesApiAdapterSync.execute()
        val phones = getPhonesApiAdapterSync.execute()

        return names?.find {
            it.name.contains(name, ignoreCase = true)
        }?.let { name ->
            return UserData(
                name = name.name,
                phone = phones?.find {
                    it.phoneId == name.phoneId
                }?.phone ?: "Phone Not Found",
                address = addresses?.find {
                    it.addressId == name.addressId
                }?.address ?: "Address Not Found"
            )
        } ?: ResponseEntity
            .status(404)
            .body("User Data Not Found")
    }
}