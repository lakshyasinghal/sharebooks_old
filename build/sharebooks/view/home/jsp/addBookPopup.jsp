<div class="container" ng-controller="BookPopupController" ng-init="init()">
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
  
            <input type="text" id="name" class="form-control margin-top-10" name="name" placeholder="Name" required>

            <input type="text" id="authorName" class="form-control margin-top-10" name="authorName" placeholder="Author Name" required>


            <div id="categoryDropdown" class="margin-top-10"">
                <input type="text" id="category" class="form-control" name="category" placeholder="Category">
                <div id="categoriesList">
                    <div class="category" ng-click="selectCategory()" ng-repeat="category in categories">
                        <span>{{category}}</span>
                    </div>
                </div>
            </div>
            <div id="subcategoryDropdown" class="margin-top-10"">
                <input type="text" id="subcategory" class="form-control" name="subcategory" placeholder="subcategory">
                <div id="subcategoriesList">
                    <div class="subcategory" ng-click="selectSubCategory()" ng-repeat="subcategory in subcategories[selectedCategory]">
                        <span>{{subcategory}}</span>
                    </div>
                </div>
            </div>


            <input type="number" id="pages" class="form-control margin-top-10" name="pages" placeholder="Pages" required>

            <input type="text" id="image" class="form-control margin-top-10" name="image" placeholder="Add image">

            <input type="text" id="available" class="form-control margin-top-10 hidden" name="available" value="1">

            <div class="margin-top-10"><input type="checkbox" ng-click="toggleBuyChecked()" id="buy"  name="buyout"> Buyout <input type="text" id="buyAmount" class="form-control margin-top-10" ng-show="buyChecked" name="buyAmount" placeholder="Enter the buyout amount"></div>

            <div class="margin-top-10"><input type="checkbox" ng-click="toggleRentChecked()" id="rent"  name="rent"> Rent <input type="text" id="rentAmount" class="form-control margin-top-10" ng-show="rentChecked" name="rentAmount" placeholder="Enter the rent amount"></div>


            <button type="button" id="addBookButton" class="btn btn-lg btn-danger btn-block margin-top-20">ADD</button>
            <!-- </form> -->
        </div>


        <div class="modal-footer">
            <div class="successMessage" id="addBookSuccessMessageDiv">{{addBookSuccessMessage}}</div>
            <div class="errorMessage" id="addBookErrorMessageDiv">{{addBookErrorMessage}}</div>
            <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
        </div>

      </div>
      
    </div>
  </div>
  
</div>

</body>
</html>
