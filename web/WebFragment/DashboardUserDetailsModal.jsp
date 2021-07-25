<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


    <!-- ===============================Modal UserDetails========================= -->
    <div
        class="modal fade bd-example-modal-lg"
        id="userDetailsModal"
        >
        <div class="modal-dialog modal-lg " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Subject Details</h5>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                        >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <ul class="nav nav-tabs mb-3" id="myTab0" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button
                            class="nav-link active"
                            id="home-tab0"
                            data-toggle="tab"
                            data-target="#overviewAddModal"
                            type="button"
                            role="tab"
                            aria-controls="home"
                            aria-selected="true"
                            >
                            Overview
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button
                            class="nav-link"
                            id="profile-tab0"
                            data-toggle="tab"
                            data-target="#addPricePackage"
                            type="button"
                            role="tab"
                            aria-controls="profile"
                            aria-selected="false"
                            >
                            Price Package
                        </button>
                    </li>
                
                </ul>

                <div class="tab-content" id="myTabContent0">
                    <!--  Overview Form -->
                    <div
                        class="tab-pane fade show active container"
                        id="overviewAddModal"
                        role="tabpanel"
                        aria-labelledby="home-tab0"
                        >                       

                        <form action="AddNewSubject" method="POST">                                                
                            <div class="form-group">
                                <label for="subjectName">Subject Name</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="subjectName"
                                    placeholder=""
                                    name="txtSubjectTitle"
                                    value=""
                                    />
                            </div>

                            <div class="form-group">
                                <label for="category">Category</label>
                                <select name="txtCategoryID" class="form-control" id="category">
                                <c:forEach items="${CATEGORY_LIST}"  var="list">                               
                                    <option value="${list.categoryID}">${list.categoryName}</option>
                                </c:forEach>

                            </select>
                        </div>

                        <div class="form-group">
                            <label for="category">OwnerID</label>
                            <select class="form-control" id="category" name="txtOwnerID">
                                <c:forEach items="${EXPERT_LIST}" var="list">
                                    <option value="${list.userID}">${list.email}-(${list.userID})</option>
                                </c:forEach >
                            </select>
                        </div>

                        <div class="form-group w-45 float-left">
                            <label for="category">Thumbnail link</label>
                            <input

                                accept="*"
                                type="file"
                                id="formFile thumbnail"
                                class="form-control" 
                                name="txtThumbnail"
                                value=""
                                onchange=" document.getElementById('blah').src = window.URL.createObjectURL(
                                                this.files[0]
                                                );
                                        document.getElementById('previewImage').style.display = 'none'"

                                />
                        </div>


                        <div class="form-check w-50 float-right">
                            <img
                                id="blah"
                                src=""
                                class="previewImage"
                                alt="your image"
                                class="preview-thumbnail"
                                style="max-width: 350px;
                                max-height: 233px;"
                                />
                            <p
                                id="previewImage"
                                style="
                                text-align: center;
                                width: 100%;
                                height: 100%;
                                transform: translateY(100px);
                                font-style: italic;
                                ">
                                Preview your image here
                            </p>
                        </div>

                        <div class="form-group">
                            <label for="BriefInfo">Brief Info</label>
                            <textarea
                                class="form-control"
                                id="BriefInfo"
                                name="txtBriefInfo"                                
                                rows="3"
                                required></textarea>
                        </div>

                        <div class="form-group">
                            <label for="Description">Description</label>
                            <textarea
                                class="form-control"
                                id="Description"
                                name="txtDescription"                                
                                rows="3"
                                required></textarea>
                        </div>

                </div>            
                <!-- End Overview Form -->

                <!-- Price Package table -->
                <div
                    class="tab-pane fade table-responsive table-hover"
                    id="addPricePackage"
                    role="tabpanel"
                    aria-labelledby="profile-tab0"
                    >
                    <table class="table table-striped table-align-middle">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Package</th>
                                <th scope="col">Duration</th>
                                <th scope="col">List Price</th>
                                <th scope="col">Sale Price</th>
                                <th scope="col">Description</th>

                                <th scope="col">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty DEFAULT_PACKAGES}">empty</c:if>
                            <c:forEach items="${DEFAULT_PACKAGES}" var="list" varStatus="count">
                                <tr>
                                    <th scope="row">
                                        ${count.count}
                                    </th>
                                    <td>${list.name}</td>
                                    <td>${list.accessduration}</td>
                                    <td>${list.listPrice}</td>
                                    <td>${list.salePrice}</td>
                                    <td>${list.description}</td>
                                    <td>${list.status}</td>
                                 
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--End Price Package table -->
              
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
<!-- ===============================End Modal UserDetails========================= -->