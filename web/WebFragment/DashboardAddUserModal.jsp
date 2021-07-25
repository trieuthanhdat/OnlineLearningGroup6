


<!-- ===============================Modal ========================= -->
<div id="addNewUserModal"
     class="modal fade bd-example-modal-lg"

     >
    <div class="modal-dialog modal-lg " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add User</h5>
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                    >
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>


            <div class="tab-content" id="myTabContent0" >
                <!--  Overview Form -->
                <div
                      id="overview"
                    class="tab-pane fade show active container"
                    role="tabpanel"
                    aria-labelledby="home-tab0"
                    >                       

                    <form action="AddNewUser" method="POST">                                                
                        <div class="form-group">
                            <label for="Email">Email</label>
                            <input
                                type="text"
                                class="form-control"
                                id="subjectName"
                                placeholder=""
                                name="txtEmail"
                                value=""
                                required
                                />
                        </div>

                        <div class="form-group">
                            <label for="Password">Password</label>
                            <input
                                type="text"
                                class="form-control"
                                id="subjectName"
                                placeholder=""
                                name="txtPassword"
                                value=""
                                required
                                />
                        </div>
                        <div class="form-group">
                            <label for="FullName">Full Name</label>
                            <input
                                type="text"
                                class="form-control"
                                id="FullName"
                                placeholder="optional"
                                name="txtFullname"
                                value=""/>
                        </div>
                        <div class="form-group">
                            <select
                                class="form-select"
                                name="txtRole"
                                aria-label="Default select example">
                                <option value="Admin">Admin</option>
                                <option value="Sale">Sale</option>
                                <option value="Marketing">Marketing</option>
                                <option value="Expert">Expert</option>
                                <option value="User">User</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="Address">Address</label>
                            <input
                                class="form-control"
                                id="Address"
                                name="txtAddress"                                
                                rows="3"
                                placeholder="optional"
                                ></input>
                        </div>

                </div>            
                <!-- End Overview Form -->


            </div>
            <!-- modal buttons -->
            <div class="modal-footer">
                <button
                    type="button"
                    class="btn btn-secondary"
                    data-dismiss="modal"
                    >
                    Back
                </button>
                <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <!--EnD modal buttons -->
        </div>        
    </div>
</div>
<!-- ===============================End Modal ========================= -->