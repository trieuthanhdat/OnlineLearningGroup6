<jsp:include page="WebFragment/DashboardHeader.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardAddUserModal.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Edit Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">

                    </h4>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-hidden="true"
                        >
                        &times;
                    </button>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <input
                        type="button"
                        class="btn btn-default"
                        data-dismiss="modal"
                        value="Cancel"
                        />
                    <input type="submit" class="btn btn-info" value="Save" />
                </div>
            </form>
        </div>
    </div>
</div>
<!--END Edit Modal HTML -->



<jsp:include page="WebFragment/DashboardSidebar.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardTopbar.jsp"></jsp:include>

    <div class="container-fluid py-4">
        <div class="row mx-1">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Quizzes</b></h2>
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
                                <th>Subject Applied to </th>
                                <th>Name</th>
                                <th>Number Of Question</th>
                                <th>Duration</th>
                                <th>Pass Rate</th>
                                <th>Level</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                    <c:forEach items="${PENDING_QUIZ_LIST}" var="list">
                        <tbody>

                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input
                                            type="checkbox"
                                            id="checkbox${list.quizID}"
                                            name="options[]"
                                            value="${list.quizID}"
                                            />
                                        <label for="checkbox${list.quizID}"></label>
                                    </span>
                                </td>
                                <td>
                                    ${list.quizID}
                                </td>
                                <td>
                                    ${list.subjectID}
                                </td>
                                <td>
                                    ${list.name}
                                </td>
                                <td>
                                    <form action="" method="post">
                                        ${list.numOfQuestions}
                                </td>
                                <td>
                                    ${list.duration}
                                </td>
                                <td>
                                    ${list.passRate}
                                </td>
                                <td>
                                    ${list.level}
                                </td>
                                <td>
                                    <select
                                        class="form-select"
                                        name="selectStatus"
                                        aria-label="Default select example">
                                        <option hidden selected  value="${list.status}">${list.status}</option>
                                        <option value="true" style='color:green'>Activated</option>
                                        <option value="false"  style='color:red'>Deactivated</option>
                                    </select>
                                </td>
                                <td>
                                    <a
                                        href="#editEmployeeModal"
                                        class="edit pop"
                                        data-toggle="modal"
                                        pageTitle="${list.title} information"
                                        pageName="DashboardUserTable.jsp">
                                        <i
                                            class="material-icons"
                                            data-toggle="tooltip"
                                            title="Edit"
                                            pageName="DashboardUserTable.jsp">&#xE254;</i></a>
                                    <button
                                        type="submit"
                                        class="delete btn"
                                        data-toggle="modal">Save</button>
                                    </form>
                                </td>
                            </tr>

                        </tbody>
                    </c:forEach>
                    <c:forEach items="${DISABLED_POSTS_LIST}" var="list">
                        <tbody>

                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input
                                            type="checkbox"
                                            id="checkbox${list.postID}"
                                            name="options[]"
                                            value="${list.postID}"
                                            />
                                        <label for="checkbox${list.postID}"></label>
                                    </span>
                                </td>
                                <td>
                                    ${list.postID}
                                </td>
                                <td>
                                    <c:forEach items="${CATEGORY_LIST}" var="catelist">
                                        <c:if test="${list.categoryID eq catelist.categoryID}">
                                            ${catelist.categoryName}
                                        </c:if>
                                    </c:forEach>

                                </td>
                                <td>
                                    ${list.title}
                                </td>
                                <td>
                                    <form action="" method="post">
                                        <select
                                            class="form-select"
                                            name="selectStatus"
                                            aria-label="Default select example">
                                            <c:if test="${list.featureFlag eq true}">
                                                <c:set value="Activated" var="status"></c:set>
                                                <c:set value="style='color:green'" var="color"></c:set>
                                            </c:if>
                                            <c:if test="${list.featureFlag eq false}">
                                                <c:set value="Deactivated" var="status"></c:set>
                                                <c:set value="style='color:red'" var="color"></c:set>
                                            </c:if>
                                            <option hidden ${color} value="${list.status}">${status}</option>
                                            <option value="true" style='color:green'>Featured</option>
                                            <option value="false"  style='color:red'>Not Featured</option>
                                        </select>
                                </td>
                                <td>

                                    <select
                                        class="form-select"
                                        name="selectStatus"
                                        aria-label="Default select example">
                                        <option hidden ${color} value="${list.status}">${status}</option>
                                        <option value="true" style='color:green'>Activated</option>
                                        <option value="false"  style='color:red'>Deactivated</option>
                                    </select>
                                </td>
                                <td>
                                    <a
                                        href="#editEmployeeModal"
                                        class="edit pop"
                                        data-toggle="modal"
                                        pageTitle="${list.title} information"
                                        pageName="DashboardUserTable.jsp">
                                        <i
                                            class="material-icons"
                                            data-toggle="tooltip"
                                            title="Edit"
                                            pageName="DashboardUserTable.jsp">&#xE254;</i></a>
                                    <button
                                        type="submit"
                                        class="delete btn"
                                        data-toggle="modal">Save</button>
                                    </form>
                                </td>
                            </tr>

                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="WebFragment/DashboardFooter.jsp"></jsp:include>
    <jsp:include page="WebFragment/DashboardConfiguration.jsp"></jsp:include>
        <script>
            $(function () {
            $(".pop").click(function () {
            var pageTitle = $(this).attr('pageTitle');
                    var pageName = $(this).attr('pageName');
                    $(".modal .modal-title").html(pageTitle);
                    $(".modal .modal-body").create(
<div class="form-group">
<label>Name</label>
<input type="text" class="form-control" required />
        </div>
            <div class="form-group">
                <label>Email</label>
                    <input type="email" class="form-control" required />
                    </div>
                        <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" required></textarea>
                        </div>
                    <div class="form-group">
                <label>Phone</label>
                <input type="text" class="form-control" required />
                </div>
                    );
                    $(".modal").modal("show");
                    $(".modal .modal-body").load(pageName);
            });
            });
        </script>
    <jsp:include page="WebFragment/DashboardEndBody.jsp"></jsp:include>