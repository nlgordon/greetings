package com.insanedev.greetings

import groovy.transform.Canonical

@Canonical
class Greeting {
    UUID id
    String greeting
    String templateName
}
