<jsp:include page="WebFragment/DashboardHeader.jsp"></jsp:include>

<jsp:include page="WebFragment/DashboardSidebar.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardTopbar.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardAddUserModal.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardEditUserModal.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${not empty EMAIL_EXIST_ERROR}">

</c:if>

<div class="container-fluid py-4">
    <div class="row mx-1">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-12">
                            <h2>Manage <b>Users</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <button
                                data-target="#addNewUserModal"
                                class="btn btn-success"
                                data-toggle="modal"
                                ><i class="material-icons">&#xE147;</i>
                                <span>Add New User</span></button>

                        </div>
                    </div>
                </div>
                <table
                    class="table table-striped table-hover"
                    id="user-table"
                    cellspacing="0"
                    width="100%"
                    >
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll" />
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Date Created</th>
                            <th>Role</th>
                            <th>status</th>
                            <th>action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${USER_LIST}" var="list">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input
                                            type="checkbox"
                                            id="checkbox${list.userID}"
                                            name="options[]"
                                            value="${list.userID}"
                                            />
                                        <label for="checkbox${list.userID}"></label>
                                    </span>
                                </td>
                                <td>${list.userID}</td>
                                <td>
                                    <c:forEach items="${USERPROFILE_LIST}" var="prList">
                                        <c:if test="${prList.email eq list.email}">
                                            <c:if test="${empty prList.avartar}">
                                                <c:set var="avatar" value="userAvatar.jpg"></c:set>
                                            </c:if>
                                            <c:if test="${not empty prList.avartar}">
                                                <c:set var="avatar" value="${prList.avartar}"></c:set>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                    <a href="#"><img
                                            src="DashboardAssets/img/Avatar/${avatar}"
                                            class="avatar"
                                            alt="Avatar"/>${list.fullName}</a>
                                </td>
                                <td>${list.createdate}</td>
                                <td>
                                    <form action="UpdateUser?userID=${list.userID}" method="post">
                                        <select
                                            class="form-select"
                                            name="selectRole"
                                            aria-label="Default select example">

                                            <option hidden value="${list.role}">${list.role}</option>
                                            <option value="Admin">Admin</option>
                                            <option value="Sale">Sale</option>
                                            <option value="Marketing">Marketing</option>
                                            <option value="Expert">Expert</option>
                                            <option value="User">User</option>

                                        </select>
                                </td>
                                <td>

                                    <select
                                        class="form-select"
                                        name="selectStatus"
                                        aria-label="Default select example">
                                        <c:if test="${list.status eq true}">
                                            <c:set value="Activated" var="status"></c:set>
                                            <c:set value="style='color:green'" var="color"></c:set>
                                        </c:if>
                                        <c:if test="${list.status eq false}">
                                            <c:set value="Deactivated" var="status"></c:set>
                                            <c:set value="style='color:red'" var="color"></c:set>
                                        </c:if>
                                        <option hidden ${color} value="${list.status}">${status}</option>
                                        <option value="true" style='color:green'>Activated</option>
                                        <option value="false"  style='color:red'>Deactivated</option>
                                    </select>
                                </td>
                                <td>
                                    <a href="GetProfile?txtUserID=${list.userID}">
                                        <button 
                                        type="button"
                                        class="btn"
                                        data-toggle="modal"
                                        data-target="#userProfileModal">
                                            View Profile
                                        </button>
                                    </a>
                                    
                                    <button
                                        type="submit"
                                        class="btn"
                                        data-toggle="modal">Save</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="WebFragment/DashboardFooter.jsp"></jsp:include>
    <jsp:include page="WebFragment/DashboardConfiguration.jsp"></jsp:include>

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
            });</script>
        <!-- MDB -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.6.0/mdb.min.js"
        ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- END  MDB -->
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"
        ></script>
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
        <script>
            function loadImage() {
                document.getElementById("blah").src = window.URL.createObjectURL(
                        this.files[0]
                        );
                document.getElementById("previewImage").style.display = "none";
            }
        </script>
    <c:if test="${not empty SEE_PROFILE}">
        <script>
            window.onload = function () {
                var button = document.getElementById("userProfileBtn");
                button.click();
            };
        </script>
    </c:if>    
</body>
</html>
