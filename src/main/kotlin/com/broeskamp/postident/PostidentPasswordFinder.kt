package com.broeskamp.postident

import net.schmizz.sshj.userauth.password.PasswordFinder
import net.schmizz.sshj.userauth.password.Resource

class PostidentPasswordFinder(password: String) : PasswordFinder {
    private val passCharArray = password.toCharArray()
    override fun reqPassword(resource: Resource<*>?): CharArray {
        return passCharArray.clone()
    }

    override fun shouldRetry(resource: Resource<*>?): Boolean {
        return false
    }
}