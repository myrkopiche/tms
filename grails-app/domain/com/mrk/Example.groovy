package com.mrk

class Example {
	String title
	String author

    static constraints = {
		title(blank: false)
		author(blank: false)
    }
}
