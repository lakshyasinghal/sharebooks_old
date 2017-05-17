<div class="container">
  <!-- <h2>Modal Example</h2> -->
  <!-- Trigger the modal with a button -->
  <!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#addBookPopup">Open Modal</button> -->

  <!-- Modal -->
  <div class="modal fade" id="addBookPopup" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h2 class="modal-title">ADD BOOK</h2>
        </div>


        <div class="modal-body">
            <!-- <form class="form-signin" method="POST" action="signUp"> -->
            <!-- <h2 class="form-signin-heading">Create a new account</h2> -->
            <input type="text" id="name" class="form-control margin-top-10" name="name" placeholder="Name" required>
            <input type="text" id="authorName" class="form-control margin-top-10" name="authorName" placeholder="Author Name" required>
            <div id="categoryDropdown" class="dropdown">
                <input type="text" id="category" class="form-control margin-top-10 dropdown-toggle" name="category" data-toggle="dropdown" placeholder="Category">
                <ul class="dropdown-menu">
                    <%
                        List<String> bookCategories = (List<String>)request.getAttribute("bookCategories");
                        // categories = new ArrayList<String>();
                        // categories.add("Maths");
                        // categories.add("Physics");
                        // categories.add("Science");
                        // categories.add("Computer Science");
                        // categories.add("Psychology");
                        // categories.add("Novel");
                        String category = null;
                        for(int i=0 ; i<bookCategories.size() ; i++){
                            category = bookCategories.get(i);
                    %>
                            <li><%=category%></li>
                    <%
                        }
                    %>
                </ul>
            </div>
            <input type="number" id="pages" class="form-control margin-top-10" name="pages" placeholder="Pages" required>
            <input type="text" id="imagePath" class="form-control margin-top-10" name="image" placeholder="Add image">
            <button id="addBookButton" class="btn btn-lg btn-danger btn-block margin-top-20">ADD</button>
            <!-- </form> -->
        </div>


        <div class="modal-footer">
            <div id="addBookMessageDiv"></div>
            <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
        </div>

      </div>
      
    </div>
  </div>
  
</div>

</body>
</html>
