<jsp:include page="WebFragment/DashboardHeader.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardAddUserModal.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="WebFragment/DashboardSidebar.jsp"></jsp:include>

<jsp:include page="WebFragment/DashboardTopbar.jsp"></jsp:include>


    <!-- Static Table Start -->
    <div class="data-table-area mg-b-15">
        <div class="container-fluid">
            <div class="row">
                <div
                    class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table responsive"
                    >
                    <div class="sparkline13-list table-wrapper">
                        <div class="sparkline13-hd">
                            <div class="main-sparkline13-hd">
                                <h1>
                                    Subject <span class="table-project-n">Management</span> Table
                                </h1>
                            </div>
                        </div>
                        <div class="sparkline13-graph">
                            <div
                                class="datatable-dashv1-list custom-datatable-overright">
                                <div id="toolbar">
                                    <select class="form-control dt-tb">
                                        <option value="">Export Basic</option>
                                        <option value="all">Export All</option>
                                        <option value="selected">Export Selected</option>
                                    </select>
                                </div>

                                <table
                                    class="table table-striped table-hover"
                                    id="table"
                                    data-toggle="table"
                                    data-pagination="true"
                                    data-search="true"
                                    data-show-columns="true"
                                    data-show-pagination-switch="true"
                                    data-show-refresh="true"
                                    data-key-events="true"
                                    data-show-toggle="true"
                                    data-resizable="true"
                                    data-cookie="true"
                                    data-cookie-id-table="saveId"
                                    data-show-export="true"
                                    data-click-to-select="true"
                                    data-toolbar="#toolbar"
                                    >
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="SubjectID">Subject ID</th>
                                            <th data-field="Category" data-editable="true">Category</th>
                                            <th data-field="Title" data-editable="true">
                                                Title
                                            </th>
                                            <th data-field="Thumbnail" data-editable="true">
                                                Thumbnail
                                            </th>
                                            <th data-field="NumberOfLessons" data-editable="true">Number of lessons</th>
                                            <th data-field="Owner" data-editable="true">Owner</th>
                                            <th data-field="Status" data-editable="true">Status</th>
                                            <th data-field="price" data-editable="true">
                                                feature flag
                                            </th>
                                            <th data-field="action">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${ENABLED_SUBJECTS_LIST}" var="list">
                                        <tr>
                                            <td></td>
                                            <td>${list.subjectID}</td>

                                            <td>
                                                <c:forEach items="${SUBJECTS_CATEGORY_LIST}" var="catelist">
                                                    <c:if test="${list.subjectCategoryID eq catelist.categoryID}">
                                                        ${catelist.categoryName}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${list.title}</td>
                                            <td>${list.thumbnail}</td>
                                            <td>${list.numOfLessons}</td>

                                            <td>${list.ownerID}</td>

                                            <c:if test="${list.status eq true}">
                                                <c:set value="Activated" var="status"></c:set>
                                                <c:set value="style='color:green'" var="color"></c:set>
                                            </c:if>
                                            <c:if test="${list.status eq false}">
                                                <c:set value="Deactivated" var="status"></c:set>
                                                <c:set value="style='color:red'" var="color"></c:set>
                                            </c:if>
                                            <td >
                                                ${status}
                                            </td>

                                            <td>${list.featureFlag}</td>
                                            <td class="datatable-ct">
                                                <button style="width:100%" class="btn" type="submit">Save</button>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                         
                        </div>
                    </div>
                        <br>
                        <div class="sparkline13-graph">
                            <div
                                class="datatable-dashv1-list custom-datatable-overright">
                                <div id="toolbar">
                                 
                                </div>

                                <table
                                    class="table table-striped table-hover"
                                    id="table"
                                    data-toggle="table"
                                    data-pagination="true"
                                    data-search="true"
                                    data-show-columns="true"
                                    data-show-pagination-switch="true"
                                    data-show-refresh="true"
                                    data-key-events="true"
                                    data-show-toggle="true"
                                    data-resizable="true"
                                    data-cookie="true"
                                    data-cookie-id-table="saveId"
                                    data-show-export="true"
                                    data-click-to-select="true"
                                    data-toolbar="#toolbar"
                                    >
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="SubjectID">Subject ID</th>
                                            <th data-field="Category" data-editable="true">Category</th>
                                            <th data-field="Title" data-editable="true">
                                                Title
                                            </th>
                                            <th data-field="Thumbnail" data-editable="true">
                                                Thumbnail
                                            </th>
                                            <th data-field="NumberOfLessons" data-editable="true">Number of lessons</th>
                                            <th data-field="Owner" data-editable="true">Owner</th>
                                            <th data-field="Status" data-editable="true">Status</th>
                                            <th data-field="price" data-editable="true">
                                                feature flag
                                            </th>
                                            <th data-field="action">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${DISABLED_SUBJECTS_LIST}" var="list">
                                        <tr>
                                            <td></td>
                                            <td>${list.subjectID}</td>

                                            <td>
                                                <c:forEach items="${SUBJECTS_CATEGORY_LIST}" var="catelist">
                                                    <c:if test="${list.subjectCategoryID eq catelist.categoryID}">
                                                        ${catelist.categoryName}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${list.title}</td>
                                            <td>${list.thumbnail}</td>
                                            <td>${list.numOfLessons}</td>

                                            <td>${list.ownerID}</td>

                                            <c:if test="${list.status eq true}">
                                                <c:set value="Activated" var="status"></c:set>
                                                <c:set value="style='color:green'" var="color"></c:set>
                                            </c:if>
                                            <c:if test="${list.status eq false}">
                                                <c:set value="Deactivated" var="status"></c:set>
                                                <c:set value="style='color:red'" var="color"></c:set>
                                            </c:if>
                                            <td >
                                                ${status}
                                            </td>

                                            <td>${list.featureFlag}</td>
                                            <td class="datatable-ct">
                                                <button style="width:100%" class="btn" type="submit">Save</button>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                         
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Static Table End -->
<footer class="footer pt-3">
    <div class="container-fluid">
        <div class="row align-items-center justify-content-lg-between">
            <div class="col-lg-6 mb-lg-0 mb-4">
                <div
                    class="copyright text-center text-sm text-muted text-lg-left"
                    >
                    ©
                    <script>
                        document.write(new Date().getFullYear());
                    </script>
                    , made with <i class="fa fa-heart"></i> inspired by
                    <a
                        href="https://www.creative-tim.com"
                        class="font-weight-bold"
                        target="_blank"
                        >Creative Tim</a
                    >
                    for a better web.
                </div>
            </div>
            <div class="col-lg-6">
                <ul
                    class="
                    nav nav-footer
                    justify-content-center justify-content-lg-end
                    "
                    >
                    <li class="nav-item">
                        <a
                            href="https://www.creative-tim.com"
                            class="nav-link text-muted"
                            target="_blank"
                            >Creative Tim</a
                        >
                    </li>
                    <li class="nav-item">
                        <a
                            href="https://www.creative-tim.com/presentation"
                            class="nav-link text-muted"
                            target="_blank"
                            >About Us</a
                        >
                    </li>
                    <li class="nav-item">
                        <a
                            href="http://blog.creative-tim.com"
                            class="nav-link text-muted"
                            target="_blank"
                            >Blog</a
                        >
                    </li>
                    <li class="nav-item">
                        <a
                            href="https://www.creative-tim.com/license"
                            class="nav-link pe-0 text-muted"
                            target="_blank"
                            >License</a
                        >
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>
</div>
</main>
<div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
        <i class="fa fa-cog py-2"> </i>
    </a>
    <div class="card shadow-lg">
        <div class="card-header pb-0 pt-3">
            <div class="float-start">
                <h5 class="mt-3 mb-0">Soft UI Configurator</h5>
                <p>See our dashboard options.</p>
            </div>
            <div class="float-end mt-4">
                <button
                    class="btn btn-link text-dark p-0 fixed-plugin-close-button"
                    >
                    <i class="fa fa-close"></i>
                </button>
            </div>
            <!-- End Toggle Button -->
        </div>
        <hr class="horizontal dark my-1" />
        <div class="card-body pt-sm-3 pt-0">
            <!-- Sidebar Backgrounds -->
            <div>
                <h6 class="mb-0">Sidebar Colors</h6>
            </div>
            <a href="javascript:void(0)" class="switch-trigger background-color">
                <div class="badge-colors my-2 text-start">
                    <span
                        class="badge filter bg-gradient-primary active"
                        data-color="primary"
                        onclick="sidebarColor(this)"
                        ></span>
                    <span
                        class="badge filter bg-gradient-dark"
                        data-color="dark"
                        onclick="sidebarColor(this)"
                        ></span>
                    <span
                        class="badge filter bg-gradient-info"
                        data-color="info"
                        onclick="sidebarColor(this)"
                        ></span>
                    <span
                        class="badge filter bg-gradient-success"
                        data-color="success"
                        onclick="sidebarColor(this)"
                        ></span>
                    <span
                        class="badge filter bg-gradient-warning"
                        data-color="warning"
                        onclick="sidebarColor(this)"
                        ></span>
                    <span
                        class="badge filter bg-gradient-danger"
                        data-color="danger"
                        onclick="sidebarColor(this)"
                        ></span>
                </div>
            </a>
            <!-- Sidenav Type -->
            <div class="mt-3">
                <h6 class="mb-0">Sidenav Type</h6>
                <p class="text-sm">Choose between 2 different sidenav types.</p>
            </div>
            <div class="d-flex">
                <button
                    class="btn bg-gradient-primary w-100 px-3 mb-2 active"
                    data-class="bg-transparent"
                    onclick="sidebarType(this)"
                    >
                    Transparent
                </button>
                <button
                    class="btn bg-gradient-primary w-100 px-3 mb-2 ms-2"
                    data-class="bg-white"
                    onclick="sidebarType(this)"
                    >
                    White
                </button>
            </div>
            <p class="text-sm d-xl-none d-block mt-2">
                You can change the sidenav type just on desktop view.
            </p>
            <!-- Navbar Fixed -->
            <div class="mt-3">
                <h6 class="mb-0">Navbar Fixed</h6>
            </div>
            <div class="form-check form-switch ps-0">
                <input
                    class="form-check-input mt-1 ms-auto"
                    type="checkbox"
                    id="navbarFixed"
                    onclick="navbarFixed(this)"
                    />
            </div>
            <hr class="horizontal dark my-sm-4" />
            <a
                class="btn bg-gradient-dark w-100"
                href="https://www.creative-tim.com/product/soft-ui-dashboard"
                >Free download</a
            >
            <a
                class="btn btn-outline-dark w-100"
                href="https://www.creative-tim.com/learning-lab/bootstrap/license/soft-ui-dashboard"
                >View documentation</a
            >
            <div class="w-100 text-center">
                <a
                    class="github-button"
                    href="https://github.com/creativetimofficial/soft-ui-dashboard"
                    data-icon="octicon-star"
                    data-size="large"
                    data-show-count="true"
                    aria-label="Star creativetimofficial/soft-ui-dashboard on GitHub"
                    >Star</a
                >
                <h6 class="mt-3">Thank you for sharing!</h6>
                <a
                    href="https://twitter.com/intent/tweet?text=Check%20Soft%20UI%20Dashboard%20made%20by%20%40CreativeTim%20%23webdesign%20%23dashboard%20%23bootstrap5&amp;url=https%3A%2F%2Fwww.creative-tim.com%2Fproduct%2Fsoft-ui-dashboard"
                    class="btn btn-dark mb-0 me-2"
                    target="_blank"
                    >
                    <i class="fab fa-twitter me-1" aria-hidden="true"></i> Tweet
                </a>
                <a
                    href="https://www.facebook.com/sharer/sharer.php?u=https://www.creative-tim.com/product/soft-ui-dashboard"
                    class="btn btn-dark mb-0 me-2"
                    target="_blank"
                    >
                    <i class="fab fa-facebook-square me-1" aria-hidden="true"></i>
                    Share
                </a>
            </div>
        </div>
    </div>
</div>
<!--   Core JS Files   -->
<script src="DashboardAssets/js/core/popper.min.js"></script>
<script src="DashboardAssets/js/core/bootstrap.min.js"></script>
<script src="DashboardAssets/js/plugins/smooth-scrollbar.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<!-- Edit/add/delete modal -->
<!-- jquery
            ============================================ -->
<script src="DashboardAssets/js/vendor/jquery-1.12.4.min.js"></script>
<!-- bootstrap JS
            ============================================ -->
<script src="DashboardAssets/js/bootstrap.min.js"></script>
<!-- wow JS
            ============================================ -->
<script src="DashboardAssets/js/wow.min.js"></script>
<!-- price-slider JS
            ============================================ -->
<script src="DashboardAssets/js/jquery-price-slider.js"></script>
<!-- meanmenu JS
            ============================================ -->
<script src="DashboardAssets/assets/js/jquery.meanmenu.js"></script>
<!-- owl.carousel JS
            ============================================ -->
<script src="DashboardAssets/js/owl.carousel.min.js"></script>
<!-- sticky JS
            ============================================ -->
<script src="DashboardAssets/js/jquery.sticky.js"></script>
<!-- scrollUp JS
            ============================================ -->
<script src="DashboardAssets/js/jquery.scrollUp.min.js"></script>
<!-- mCustomScrollbar JS
            ============================================ -->
<script src="DashboardAssets/js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="DashboardAssets/js/scrollbar/mCustomScrollbar-active.js"></script>
<!-- metisMenu JS
            ============================================ -->
<script src="DashboardAssets/js/metisMenu/metisMenu.min.js"></script>
<script src="DashboardAssets/js/metisMenu/metisMenu-active.js"></script>
<!-- data table JS
            ============================================ -->
<script src="DashboardAssets/js/data-table/bootstrap-table.js"></script>
<script src="DashboardAssets/js/data-table/tableExport.js"></script>
<script src="DashboardAssets/js/data-table/data-table-active.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-table-editable.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-editable.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-table-resizable.js"></script>
<script src="DashboardAssets/js/data-table/colResizable-1.5.source.js"></script>
<script src="DashboardAssets/js/data-table/bootstrap-table-export.js"></script>
<!--  editable JS
            ============================================ -->
<script src="DashboardAssets/js/editable/jquery.mockjax.js"></script>
<script src="DashboardAssets/js/editable/mock-active.js"></script>
<script src="DashboardAssets/js/editable/select2.js"></script>
<script src="DashboardAssets/js/editable/moment.min.js"></script>
<script src="DashboardAssets/js/editable/bootstrap-datetimepicker.js"></script>
<script src="DashboardAssets/js/editable/bootstrap-editable.js"></script>
<script src="DashboardAssets/js/editable/xediable-active.js"></script>
<!-- Chart JS
            ============================================ -->
<script src="DashboardAssets/js/chart/jquery.peity.min.js"></script>
<script src="DashboardAssets/js/peity/peity-active.js"></script>
<!-- tab JS
            ============================================ -->
<script src="DashboardAssets/js/tab.js"></script>
<!-- plugins JS
            ============================================ -->
<script src="DashboardAssets/js/plugins.js"></script>
<!-- main JS
            ============================================ -->
<script src="DashboardAssets/js/main.js"></script>
<!-- tawk chat JS
            ============================================ -->
<script src="DashboardAssets/js/tawk-chat.js"></script>

<script>
                        $(document).ready(function () {
                            // Activate tooltip
                            $('[data-toggle="tooltip"]').tooltip();

                            // Select/Deselect checkboxes
                            var checkbox = $('table tbody input[type="checkbox"]');
                            $("#selectAll").click(function () {
                                if (this.checked) {
                                    checkbox.each(function () {
                                        this.checked = true;
                                    });
                                } else {
                                    checkbox.each(function () {
                                        this.checked = false;
                                    });
                                }
                            });
                            checkbox.click(function () {
                                if (!this.checked) {
                                    $("#selectAll").prop("checked", false);
                                }
                            });
                        });
</script>

<script>
    var win = navigator.platform.indexOf("Win") > -1;
    if (win && document.querySelector("#sidenav-scrollbar")) {
        var options = {
            damping: "0.5",
        };
        Scrollbar.init(document.querySelector("#sidenav-scrollbar"), options);
    }
</script>
<!-- Github buttons -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
<script src="DashboardAssets/js/soft-ui-dashboard.min.js?v=1.0.2"></script>
</body>
</html>
