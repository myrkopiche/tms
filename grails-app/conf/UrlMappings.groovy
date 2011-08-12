class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/home")
		"/index"(view:"/index")
		"500"(view:'/error')
	}
}
