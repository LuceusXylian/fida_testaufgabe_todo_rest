package com.example.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@RequestMapping(path = "/")
	public String todo_html_page() {
		return "<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"    <meta charset=\"utf-8\">\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
				"    <title>Todo App</title>\n" +
				"    <style>\n" +
				"        html,body {\n" +
				"            margin:0;\n" +
				"            padding:0;\n" +
				"        }\n" +
				"        * {\n" +
				"            font-family: Arial, sans-serif;\n" +
				"        }\n" +
				"        .row { padding: 4px; }\n" +
				"        .col { display: inline-block; vertical-align: middle; padding: 4px; }\n" +
				"    </style>\n" +
				"</head>\n" +
				"\n" +
				"<body>\n" +
				"    <div id=\"app\"></div>\n" +
				"        <div style=\"margin-bottom: 20px;\">\n" +
				"            <input type=\"text\" id=\"todo_add_description\" placeholder=\"Description\">\n" +
				"            <input type=\"date\" id=\"todo_add_deadline\" placeholder=\"Date of Deadline\">\n" +
				"            <button id=\"todo_add_submit\">Add Todo</button>\n" +
				"        </div>\n" +
				"        <div id=\"todo_list\"></div>\n" +
				"    </div>\n" +
				"\n" +
				"</body>\n" +
				"</html>\n" +
				"<script>\n" +
				"        function api_request(method, url, params) {\n" +
				"            return new Promise(function(resolve, reject) {\n" +
				"                var http = new XMLHttpRequest();\n" +
				"                http.onreadystatechange = function () {\n" +
				"                    if (this.readyState === 4) {\n" +
				"                        if (this.status === 200) {\n" +
				"                            resolve(this.responseText);\n" +
				"                        } else {\n" +
				"                            reject(this.responseText, this.status);\n" +
				"                            console.error(this);\n" +
				"                        }\n" +
				"                    }\n" +
				"                };\n" +
				"                var urlEncodedParams = \"\", bool = false;\n" +
				"                var params_array = Object.entries(params);\n" +
				"                for (var a = 0; a < params_array.length; a++) {\n" +
				"                    var entry = params_array[a];\n" +
				"                    if (bool)\n" +
				"                        urlEncodedParams += '&';\n" +
				"                    else\n" +
				"                        bool = true;\n" +
				"                    urlEncodedParams += encodeURIComponent(entry[0]) + '=' + encodeURIComponent(entry[1]);\n" +
				"                }\n" +
				"                if (method === \"GET\") {\n" +
				"                    if (params_array.length > 0)\n" +
				"                        url += \"?\" + urlEncodedParams;\n" +
				"                    http.open(method, url);\n" +
				"                    http.send();\n" +
				"                } else {\n" +
				"                    http.open(method, url);\n" +
				"                    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');\n" +
				"                    http.send(urlEncodedParams);\n" +
				"                }\n" +
				"            });\n" +
				"        }\n" +
				"\n" +
				"        const todo_list = document.getElementById(\"todo_list\");\n" +
				"        function load_todo_list() {\n" +
				"            api_request(\"GET\", \"/api/v1/todo/list\", {})\n" +
				"            .then(function(data) {\n" +
				"                const list = JSON.parse(data);\n" +
				"                todo_list.innerHTML = \"\";\n" +
				"                console.log(list);\n" +
				"\n" +
				"                for(let i=0; i<list.length; i++) {\n" +
				"                    const todo = list[i];\n" +
				"\n" +
				"                    const row = document.createElement(\"div\");\n" +
				"                    row.className = \"row\";\n" +
				"\n" +
				"                    // description\n" +
				"                    const col_description = document.createElement(\"div\");\n" +
				"                    col_description.className = \"col\";\n" +
				"                    col_description.innerText = todo.description;\n" +
				"                    row.append(col_description);\n" +
				"\n" +
				"                    // deadline\n" +
				"                    const col_deadline = document.createElement(\"div\");\n" +
				"                    col_deadline.className = \"col\";\n" +
				"                    col_deadline.innerText = todo.deadlineDate;\n" +
				"                    row.append(col_deadline);\n" +
				"\n" +
				"                    // delete button\n" +
				"                    const col_delete_button = document.createElement(\"div\");\n" +
				"                    col_delete_button.className = \"col\";\n" +
				"                    row.append(col_delete_button);\n" +
				"\n" +
				"                    const delete_button = document.createElement(\"button\");\n" +
				"                    delete_button.innerText = \"Delete Todo\";\n" +
				"                    col_delete_button.append(delete_button);\n" +
				"\n" +
				"                    const todo_id = todo.id;\n" +
				"                    delete_button.addEventListener(\"click\", function() {\n" +
				"                        api_request(\"POST\", \"/api/v1/todo/delete\", { id: todo_id })\n" +
				"                        .then(function() { load_todo_list(); });\n" +
				"                    });\n" +
				"\n" +
				"\n" +
				"                    todo_list.append(row);\n" +
				"                }\n" +
				"            });\n" +
				"        }\n" +
				"        load_todo_list();\n" +
				"\n" +
				"        // add todo form\n" +
				"        const todo_add_description = document.getElementById(\"todo_add_description\");\n" +
				"        const todo_add_deadline = document.getElementById(\"todo_add_deadline\");\n" +
				"        const todo_add_submit = document.getElementById(\"todo_add_submit\");\n" +
				"\n" +
				"        todo_add_submit.addEventListener(\"click\", function() {\n" +
				"            api_request(\"POST\", \"/api/v1/todo/add\", {\n" +
				"                description: todo_add_description.value,\n" +
				"                deadlineDate: todo_add_deadline.value\n" +
				"            })\n" +
				"            .then(function() { load_todo_list(); });\n" +
				"        });\n" +
				"    </script>\n";

	}
}
