package com.startup.foot6like.test.models

class User {

    var email: String? = null
    var password: String? = null

    var phone: String? = null
    var name: String? = null
    //

    constructor()
    constructor(name: String?, email: String?, password: String?, phone: String?) {

        this.email = email
        this.password = password

        this.name = name
        this.phone = phone
    }
}