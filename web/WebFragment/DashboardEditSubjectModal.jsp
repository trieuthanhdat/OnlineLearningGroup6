 <div
        class="modal fade bd-example-modal-lg"
        id="subjectDetailsModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
        >
        <div class="modal-dialog modal-lg" role="document">
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
                            data-mdb-toggle="tab"
                            data-mdb-target="#overview"
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
                            data-mdb-toggle="tab"
                            data-mdb-target="#pricePackage"
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
                        id="overview"
                        role="tabpanel"
                        aria-labelledby="home-tab0"
                        >
                        <form>
                            <div class="form-group">
                                <label for="subjectName">Subject Name</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="subjectName"
                                    placeholder=""
                                    />
                            </div>
                            <div class="form-group">
                                <label for="category">Category</label>
                                <select class="form-control" id="category">
                                    <option>C1</option>
                                    <option>C2</option>
                                    <option>C3</option>
                                    <option>C4</option>
                                    <option>C5</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="category">OwnerID</label>
                                <select class="form-control" id="category">
                                    <option>O1</option>
                                    <option>O1</option>
                                    <option>O1</option>
                                    <option>O1</option>
                                    <option>06</option>
                                </select>
                            </div>
                            <div class="form-group w-45 float-left">
                                <label for="category">Thumbnail link</label>
                                <input
                                    accept="*"
                                    type="file"
                                    id="formFile thumbnail"
                                    class="form-control"
                                    name="txtThumbnailLink"
                                    value=""
                                    onchange=" document.getElementById('blah').src = window.URL.createObjectURL(
                                                    this.files[0]
                                                    );
                                            document.getElementById('previewImage').style.display = 'none'"
                                    required
                                    />
                            </div>
                            <div class="form-check w-50 float-right">
                                <img
                                    id="blah"
                                    src=""
                                    class="previewImage"
                                    alt="your image"
                                    class="preview-thumbnail"
                                    />
                                <p
                                    id="previewImage"
                                    style="
                                    text-align: center;
                                    width: 100%;
                                    height: 100%;
                                    transform: translateY(100px);
                                    font-style: italic;
                                    "
                                    >
                                    Preview your image here
                                </p>
                            </div>
                            <div class="form-check w-25 m-2">
                                <input
                                    class="form-check-input"
                                    type="checkbox"
                                    value=""
                                    id="featuredSubject"
                                    />
                                <label class="form-check-label" for="featuredSubject">
                                    Featured Subject
                                </label>
                            </div>

                            <div class="form-group w-25">
                                <label for="status">Status</label>
                                <select class="form-control" id="status">
                                    <option>active</option>
                                    <option>inactive</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="Description">Description</label>
                                <textarea
                                    class="form-control"
                                    id="Description"
                                    rows="3"
                                    ></textarea>
                            </div>
                        </form>
                    </div>
                    <!-- End Overview Form -->
                    <!-- Price Package table -->
                    <div
                        class="tab-pane fade table-responsive table-hover"
                        id="pricePackage"
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
                                    <th scope="col">Status</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1(id)</th>
                                    <td>3 Months Access</td>
                                    <td>3</td>
                                    <td>3600</td>
                                    <td>3200</td>
                                    <td>active</td>
                                    <td>
                                        <a href="" class="p-2">Edit</a>
                                        <a href="">Deactivate</a>
                                    </td>
                                </tr>
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
                        back
                    </button>
                    <button type="button" class="btn btn-primary">Submit</button>
                </div>
                <!--EnD modal buttons -->
            </div>
        </div>
    </div>
    <!-- ===============================End Modal SubjectDetails========================= -->
