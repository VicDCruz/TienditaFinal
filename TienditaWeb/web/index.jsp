<%-- 
    Document   : index
    Created on : 16/12/2019, 09:51:51 PM
    Author     : marti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Almacén</title>
        
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        
    </head>
    <body>
        <header>
            <div class="collapse bg-dark" id="navbarHeader">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-md-7 py-4">
                            <h4 class="text-white">About</h4>
                            <p class="text-muted">Add some information about the album below, the author, or any other background context. Make it a few sentences long so folks can pick up some informative tidbits. Then, link them off to some social networking sites or contact information.</p>
                        </div>
                        <div class="col-sm-4 offset-md-1 py-4">
                            <h4 class="text-white">Contact</h4>
                            <ul class="list-unstyled">
                                <li><a href="#" class="text-white">Follow on Twitter</a></li>
                                <li><a href="#" class="text-white">Like on Facebook</a></li>
                                <li><a href="#" class="text-white">Email me</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="navbar navbar-dark bg-dark shadow-sm">
                <div class="container d-flex justify-content-between">
                    <a href="#" class="navbar-brand d-flex align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="mr-2" viewBox="0 0 24 24" focusable="false"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>
                        <strong>Tienda</strong>
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
            </div>
        </header>

        <main role="main">

            <section class="jumbotron text-center">
                <div class="container">
                    <h1>Al tiro con la rata</h1>
                    <p class="lead text-muted">Todos los productos que imagines al alcance de tu mano</p>
                </div>
            </section>

            <div class="album py-5 bg-light">
                <div class="container">

                    <div class="row">
                        
                        
                        
                        <% 
                            
                            try {
                                ws.WSAlmacen_Service service = new ws.WSAlmacen_Service();
                                ws.WSAlmacen port = service.getWSAlmacenPort();
                                // TODO process result here
                                java.util.List<ws.Product> result = port.findAll();
                                
                                for(int i=0;i<result.size();i++){
                                    out.println("<div class='col-md-4'>");
                                    out.println("<div class='card mb-4 shadow-sm'>");
                                    out.println("<svg class='bd-placeholder-img card-img-top' width='100%' height='225' xmlns='http://www.w3.org/2000/svg' preserveAspectRatio='xMidYMid slice' focusable='false' role='img' aria-label='Placeholder: Thumbnail'><title>Placeholder</title><rect width='100%' height='100%' fill='#55595c'/><text x='50%' y='50%' fill='#eceeef' dy='.3em'>Thumbnail</text></svg>");
                                    out.println("<div class='card-body'>");
                                    out.println("<p class='card-text'>" + result.get(i).getDescription() + "</p>");
                                    
                                    out.println("<form method='post' action='validaCantidad.jsp'>");
                                    out.println("<input type='hidden' name='id_producto' value='"+result.get(i).getProductId()+"'>");
                                    out.println("<div class='row'>");
                                    out.println("<div class='col-md-12'>");
                                    out.println("<div class='input-group'>");
                                    out.println("<div class='input-group-prepend'>");
                                    out.println("<div class='input-group-text' id='btnGroupAddon'>Cantidad</div>");
                                    out.println("</div>");
                                    out.println("<input type='text' class='form-control' name='cantidad'>");
                                    out.println("</div>");
                                    out.println("</div>");
                                    out.println("<div class='col-md-6 text-left'>");
                                    out.println("<button type='submit' class='btn btn-sm btn-outline-secondary'>Comprar</button>");
                                    out.println("</div>");
                                    out.println("<div class='col-md-6 text-right'>");
                                    out.println("<p>$"+ result.get(i).getPurchaseCost() +"</p>");
                                    out.println("</div>");
                                    out.println("</div>");
                                    out.println("</form>");

                                    out.println("</div>");
                                    out.println("</div>");
                                    out.println("</div>");
                                }
                                
                            } catch (Exception ex) {
                                // TODO handle custom exceptions here
                            }
                        
                            

                        %>
                        
                        
                        
                    </div>
                </div>
            </div>

        </main>

        <footer class="text-muted">
            <div class="container">
                <p class="float-right">
                    <a href="#">Back to top</a>
                </p>
                <p>Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
                <p>New to Bootstrap? <a href="https://getbootstrap.com/">Visit the homepage</a> or read our <a href="/docs/4.4/getting-started/introduction/">getting started guide</a>.</p>
            </div>
        </footer>
    <%-- start web service invocation --%><hr/>

    <%-- end web service invocation --%><hr/>
</html>
