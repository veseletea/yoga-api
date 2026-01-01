package com.example.yoga.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Yoga API</title>
                <style>
                    body {
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                        max-width: 800px;
                        margin: 50px auto;
                        padding: 20px;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        min-height: 100vh;
                    }
                    .container {
                        background: white;
                        border-radius: 16px;
                        padding: 40px;
                        box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                    }
                    h1 {
                        color: #667eea;
                        margin-bottom: 10px;
                    }
                    .subtitle {
                        color: #666;
                        margin-bottom: 30px;
                    }
                    h2 {
                        color: #333;
                        border-bottom: 2px solid #667eea;
                        padding-bottom: 10px;
                        margin-top: 30px;
                    }
                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin: 20px 0;
                    }
                    th, td {
                        padding: 12px;
                        text-align: left;
                        border-bottom: 1px solid #eee;
                    }
                    th {
                        background: #f8f9fa;
                        color: #333;
                    }
                    code {
                        background: #f1f1f1;
                        padding: 2px 8px;
                        border-radius: 4px;
                        font-size: 14px;
                    }
                    a {
                        color: #667eea;
                        text-decoration: none;
                    }
                    a:hover {
                        text-decoration: underline;
                    }
                    .method {
                        display: inline-block;
                        padding: 4px 8px;
                        border-radius: 4px;
                        font-size: 12px;
                        font-weight: bold;
                        color: white;
                    }
                    .get { background: #28a745; }
                    .post { background: #007bff; }
                    .put { background: #ffc107; color: #333; }
                    .delete { background: #dc3545; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>Yoga API</h1>
                    <p class="subtitle">REST API pentru gestionarea pozitiilor de yoga</p>

                    <h2>Endpoints disponibile</h2>
                    <table>
                        <tr>
                            <th>Metoda</th>
                            <th>Endpoint</th>
                            <th>Descriere</th>
                        </tr>
                        <tr>
                            <td><span class="method get">GET</span></td>
                            <td><a href="/api/poses">/api/poses</a></td>
                            <td>Toate pozitiile</td>
                        </tr>
                        <tr>
                            <td><span class="method get">GET</span></td>
                            <td><a href="/api/poses/1">/api/poses/{id}</a></td>
                            <td>O pozitie dupa ID</td>
                        </tr>
                        <tr>
                            <td><span class="method post">POST</span></td>
                            <td><code>/api/poses</code></td>
                            <td>Creeaza pozitie noua</td>
                        </tr>
                        <tr>
                            <td><span class="method put">PUT</span></td>
                            <td><code>/api/poses/{id}</code></td>
                            <td>Actualizeaza pozitie</td>
                        </tr>
                        <tr>
                            <td><span class="method delete">DELETE</span></td>
                            <td><code>/api/poses/{id}</code></td>
                            <td>Sterge pozitie</td>
                        </tr>
                        <tr>
                            <td><span class="method get">GET</span></td>
                            <td><a href="/api/poses/beginners?maxLevel=2">/api/poses/beginners</a></td>
                            <td>Pozitii pentru incepatori</td>
                        </tr>
                        <tr>
                            <td><span class="method get">GET</span></td>
                            <td><a href="/api/poses/search?name=warrior">/api/poses/search?name=...</a></td>
                            <td>Cauta dupa nume</td>
                        </tr>
                    </table>

                    <h2>Resurse</h2>
                    <p><a href="/h2-console">H2 Database Console</a> (JDBC URL: <code>jdbc:h2:mem:yogadb</code>, user: <code>sa</code>)</p>

                    <h2>Tehnologii</h2>
                    <p>Spring Boot 3.2 | Spring Data JPA | H2 Database | Java 17</p>
                </div>
            </body>
            </html>
            """;
    }
}
