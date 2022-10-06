package com.broeskamp.postident

import net.schmizz.sshj.userauth.password.PasswordFinder
import net.schmizz.sshj.userauth.password.Resource

class PostidentPasswordFinder(private val password: String) : PasswordFinder {

    override fun reqPassword(resource: Resource<*>?): CharArray {
        return password.toCharArray().clone()
    }

    override fun shouldRetry(resource: Resource<*>?): Boolean {
        return false
    }
}