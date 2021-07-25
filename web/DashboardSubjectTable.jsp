<jsp:include page="WebFragment/DashboardHeader.jsp"></jsp:include>

<%--<jsp:include page="WebFragment/DashboardAddUserModal.jsp"></jsp:include>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="WebFragment/DashboardSidebar.jsp"></jsp:include>

<jsp:include page="WebFragment/DashboardTopbar.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardAddNewSubjectModal.jsp"></jsp:include>

    <!-- Button trigger modal -->
    <button
        hidden
        type="button"
        class="btn btn-primary"
        data-toggle="modal"
        data-target="#subjectDetailsModal"
        id="subjectDetailBtn"
        >                
    </button>
    <button
        hidden
        type="button"
        class="btn btn-primary"
        data-toggle="modal"
        data-target="#editPackageModal"
        id="editPackageBtn"
        >        
    </button>

    <button
        hidden
        type="button"
        class="btn btn-primary"
        data-toggle="modal"
        data-target="#editLessonModal"
        id="editLessonBtn"
        >        
    </button>
    <button
        hidden
        type="button"
        class="btn btn-primary"
        data-toggle="modal"
        data-target="#quizDetailsModal"
        id="editQuizBtn"
        >        
    </button>
    <!-- Button trigger package modal -->

    <!-- ===============================Modal SubjectDetails========================= -->
    <div
        class="modal fade bd-example-modal-lg"
        id="subjectDetailsModal"
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
                            data-target="#overview"
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
                            data-target="#pricePackage"
                            type="button"
                            role="tab"
                            aria-controls="profile"
                            aria-selected="false"
                            >
                            Price Package
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button
                            class="nav-link"
                            id="lessonList-tab0"
                            data-mdb-toggle="tab"
                            data-mdb-target="#lessonList"
                            type="button"
                            role="tab"
                            aria-controls="profile"
                            aria-selected="false"
                            >
                            Lesson List
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button
                            class="nav-link"
                            id="lessonList-tab0"
                            data-mdb-toggle="tab"
                            data-mdb-target="#QuizList"
                            type="button"
                            role="tab"
                            aria-controls="profile"
                            aria-selected="false"
                            >
                            Quiz List
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

                        <form action="UpdateSubjectDetails" method="POST">                                                
                            <div class="form-group">
                                <label for="subjectName">Subject Name</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="subjectName"
                                    placeholder=""
                                    name="txtSubjectTitle"
                                    value="${CURRENT_SUBJECT.title}"
                                />
                        </div>

                        <div class="form-group">
                            <label for="category">Category</label>
                            <select name="txtCategoryID" class="form-control" id="category">
                                <option hidden value="${list.categoryID}">${list.categoryName}</option>
                                <c:forEach items="${CATEGORY_LIST}" var="list">
                                    <c:if test="${list.categoryID eq CURRENT_SUBJECT.subjectCategoryID}">
                                        <option hidden selected value="${list.categoryID}">${list.categoryName}</option>
                                    </c:if>
                                    <option value="${list.categoryID}">${list.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="category">OwnerID</label>
                            <select class="form-control" id="category">
                                <option selected value="${CURRENT_SUBJECT.ownerID}">${CURRENT_SUBJECT.ownerID}</option>
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
                                value="${CURRENT_SUBJECT.thumbnail}"
                                onchange=" document.getElementById('blah').src = window.URL.createObjectURL(
                                                this.files[0]
                                                );
                                        document.getElementById('previewImage').style.display = 'none'"
                                />
                        </div>


                        <div class="form-check w-50 float-right">
                            <img

                                id="blah"
                                src="assets/img/${CURRENT_SUBJECT.thumbnail}"
                                class="previewImage"
                                alt="your image"
                                class="preview-thumbnail"
                                style="max-width: 350px;
                                max-height: 233px;"
                                />
                            <c:if test="${empty CURRENT_SUBJECT.thumbnail}">
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
                            </c:if>
                        </div>

                        <div class="form-check w-25 m-2">
                            <c:if test="${CURRENT_SUBJECT.featureFlag eq true}"> 
                                <c:set value="checked" var="check"></c:set> 
                            </c:if>
                            <c:if test="${CURRENT_SUBJECT.featureFlag eq false}"> 
                                <c:set value="unchecked" var="check"></c:set> 
                            </c:if>
                            <c:if test="${CURRENT_USER.role ne 'Admin'}"> 
                                <c:set value="disabled" var="status"></c:set> 
                            </c:if>
                            <input
                                ${status}
                                name="txtFeatureFlag"
                                class="form-check-input"
                                type="checkbox"
                                value="ON"
                                id="featuredSubject"
                                ${check}
                                />
                            <label class="form-check-label" for="featuredSubject">
                                Featured Subject
                            </label>
                        </div>

                        <div class="form-group w-25">
                            <label for="status">Status</label>
                            <select name="txtStatus" class="form-control" id="status">
                                <c:if test="${CURRENT_SUBJECT.status eq true}"> 
                                    <c:set value="active" var="status"></c:set> 
                                </c:if>
                                <c:if test="${CURRENT_SUBJECT.status ne true}"> 
                                    <c:set value="inactive" var="status"></c:set> 
                                </c:if>
                                <option hidden value="${CURRENT_SUBJECT.status}">${status}</option>
                                <option value="true">active</option>
                                <option value="false">inactive</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="BriefInfo">Brief Info</label>
                            <textarea
                                class="form-control"
                                id="BriefInfo"
                                name="txtBriefInfo"                                
                                rows="3">${CURRENT_SUBJECT.briefInfo}</textarea>
                        </div>

                        <div class="form-group">
                            <label for="Description">Description</label>
                            <textarea
                                class="form-control"
                                id="Description"
                                name="txtDescription"                                
                                rows="3">${CURRENT_SUBJECT.details.description}</textarea>
                        </div>
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
                                <th scope="col">Description</th>

                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${CURRENT_SUBJECT.packages}" var="list" varStatus="count">
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
                                    <td>
                                        <a href="GetEditPackageInfo?txtPackageID=${list.packageID}" class="p-2"><button  class="btn"
                                                                                                                         id="editPackageBtn"
                                                                                                                         type="button" data-toggle="modal" data-target="#editPackageModal" >Edit</button></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--End Price Package table -->

                <!-- Lesson list table -->
                <div
                    class="tab-pane fade table-responsive table-hover"
                    id="lessonList"
                    role="tabpanel"
                    aria-labelledby="profile-tab0"
                    >
                    <div class="form-check w-25 m-2 float-right">
                        <button
                            class="btn"
                            id="addLessonBtn"
                            type="button"
                            class="btn btn-primary"
                            data-toggle="modal"
                            data-target="#addLessonModal"
                            >
                            Add Lesson
                        </button>
                    </div>
           
                    <table class="table table-striped table-align-middle">
                        <thead>
                            <tr>
                                <th scope="col">Lesson ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Type</th>
                                <th scope="col">Order</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${SUB_LESSONS}" var="lesson">
                                <tr>
                                    <th scope="row">${lesson.lessonID}</th>
                                    <td>${lesson.name}</td>
                                    <td>${lesson.type}</td>
                                    <td>${lesson.order}</td>
                                    <td>${lesson.status}</td>
                                    <td>
                                        <a href="GetEditLessonInfo?txtLessonID=${lesson.lessonID}" class="p-2">Edit</a>
                                        <a href="">Deactivate</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--End lesson list table -->

                <!-- Quiz list table -->
                <div
                    class="tab-pane fade table-responsive table-hover"
                    id="QuizList"
                    role="tabpanel"
                    aria-labelledby="profile-tab0"
                    >

                    <div class="form-check w-25 m-2 float-right">
                        <button
                            class="btn"
                            id="quizAddingBtn"
                            type="button"
                            class="btn btn-primary"
                            data-toggle="modal"
                            data-target="#quizAddingModal"
                            >
                            Add Quiz
                        </button>
                    </div>
                    <table class="table table-striped table-align-middle">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Number of Question</th>
                                <th scope="col">Duration</th>
                                <th scope="col">Pass Rate</th>
                                <th scope="col">Level</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${QUIZ_LIST}" var="quiz">
                                <tr>
                                    <th scope="row">${quiz.quizID}</th>
                                    <td>${quiz.name}</td>
                                    <td>${quiz.numOfQuestions}</td>
                                    <td>${quiz.duration}</td>
                                    <td>${quiz.passRate}</td>
                                    <td>${quiz.level}</td>
                                    <td>
                                        <a href="ChangeQuizStatus?quizID=${quiz.quizID}&status=0">activate</a>
                                        <a href="ChangeQuizStatus?quizID=${quiz.quizID}&status=1">deactivate</a>
                                    </td>
                                    <td>
                                        <a href="GetQuizInfo?quizID=${quiz.quizID}" class="p-2">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--End Quiz list table -->
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
                </form>
            </div>
            <!--EnD modal buttons -->
        </div>        
    </div>
</div>
<!-- ===============================End Modal Subject Details========================= -->

<!-- ===============================Start Modal Package========================= -->
<c:if test="${not empty CURRENT_SUBJECT}">

    <!-- edit Modal HTML -->
    <div id="editPackageModal" class="modal fade" >
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="UpdatePackage" method="POST">
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Package</h4>
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
                        <div class="form-group">
                            <input type="text" name="txtPackageID" class="form-control" value="${CURRENT_PACKAGE.packageID}" required />

                            <label>Package</label>
                            <input type="text" name="txtPackageName" class="form-control" value="${CURRENT_PACKAGE.name}" required />
                        </div>
                        <div class="form-group">
                            <label>Duration/month</label>
                            <input type="number" name="txtAccessDuration" min="0" step="1" class="form-control" value="${CURRENT_PACKAGE.accessduration}" required />
                        </div>
                        <div class="form-group">
                            <label>List Price(VND)</label>
                            <input type="number" name="txtListPrice" min="0" step="1000" class="form-control" value="${CURRENT_PACKAGE.listPrice}" required></input>
                        </div>
                        <div class="form-group">
                            <label>Sale Price(VND)</label>
                            <input type="number" name="txtSalePrice" min="0" step="1000" class="form-control"  value="${CURRENT_PACKAGE.salePrice}"  required />
                        </div>
                        <div class="form-group">
                            <label>status</label>
                            <select class="form-control" name="txtStatus" id="status">
                                <c:if test="${list.status eq true}">
                                    <c:set value="activated" var="status"></c:set>
                                    <c:set value="style='color:green'" var="color"></c:set>
                                </c:if>
                                <c:if test="${list.status eq false}">
                                    <c:set value="deactivated" var="status"></c:set>
                                    <c:set value="style='color:red'" var="color"></c:set>
                                </c:if>
                                <option hidden ${color} selected value="${CURRENT_PACKAGE.status}">${status}</option>
                                <option style='color:green' value="true">activate</option>
                                <option style='color:red' value="false">deactivate</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea type="text" name="txtDescription" class="form-control"  required >${CURRENT_PACKAGE.description}"</textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input
                            type="button"
                            class="btn btn-default"
                            data-dismiss="modal"
                            value="Cancel"
                            />
                        <input type="submit" class="btn btn-success" value="Edit" />
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- ===============================End Modal Package========================= -->

</c:if>

<!-- ===============================Modal Quiz Adding========================= -->
<div
    class="modal fade bd-example-modal-lg"
    id="quizAddingModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
    >
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Quiz Details</h5>
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                    >
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <ul class="nav nav-tabs mb-3" id="quizTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button
                        class="nav-link active"
                        id="quiz-tab"
                        data-mdb-toggle="tab"
                        data-mdb-target="#quizOverview"
                        type="button"
                        role="tab"
                        aria-controls="home"
                        aria-selected="true"
                        >
                        Overview
                    </button>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent0">
                <form action="MakeQuiz" method="post">
                    <input name="subjectID" value="${CURRENT_SUBJECT.subjectID}" hidden/>
                    <!--  Overview Form -->
                    <div
                        class="tab-pane fade show active container"
                        id="quizOverview"
                        role="tabpanel"
                        aria-labelledby="home-tab0"
                        >
                        <div class="form-group w-50" style="    margin-right: 10%;
                             display: inline-block;">
                            <label for="quizName">Name <span class="starcheck">*</span></label>
                            <input
                                name="name"
                                type="text"
                                class="form-control"
                                id="quizName"
                                placeholder=""
                                />
                        </div>
                        <div class="form-group w-30 " style="    display: inline-block;">
                            <label for="duration">Duration(in minutes) <span class="starcheck">*</span></label>
                            <input
                                name="duration"
                                type="number"
                                class="form-control"
                                id="duration"
                                placeholder=""
                                min="1"
                                max="121    "
                                />

                        </div>
                        <div class="form-group w-50" style="    margin-right: 10%;
                             display: inline-block;">
                            <label for="lesson">lesson <span class="starcheck">*</span></label>
                            <select name="lessonID" class="form-control" id="lesson">
                                <c:forEach items="${LEARN_LESSON}" var="list">
                                    <option value="${list.lessonID}" >${list.name}</option>
                                </c:forEach>
                                    <option value="Non-lesson">non-lesson</option>
                            </select>
                        </div>

                        <div class="form-group w-30 " style="display: inline-block;">
                            <label for="level">Quiz level <span class="starcheck">*</span></label>
                            <select name="level" class="form-control" id="level">
                                <option value="easy">Easy</option>
                                <option value="medium">Medium</option>
                                <option value="hard">Hard</option>
                            </select>
                        </div>
                        <div class="form-group w-25 " style="display: inline-block;">
                            <label for="duration">number of questions <span class="starcheck">*</span></label>
                            <input
                                name="numOfQuestions"
                                type="number"
                                class="form-control"
                                id="duration"
                                placeholder=""
                                min="1"
                                max="121"
                                />

                        </div>
                        <div class="form-group w-25 " style="display: inline-block;
                             margin-right: 10%;">
                            <label for="passrate">Pass rate(%) <span class="starcheck">*</span></label>
                            <input
                                name="passRate"
                                type="number"
                                class="form-control"
                                id="passrate"
                                placeholder=""/>
                        </div>
                    </div>
            </div>
            <!-- End Overview Form -->

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
            </div>
            <!--EnD modal buttons -->
            </form>
        </div>
    </div>
</div>
<!-- ===============================End Modal Quiz Adding========================= -->

<jsp:include page="WebFragment/DashboardAddNewLessonModal.jsp"></jsp:include>

<!-- ===============================Modal Lesson Details========================= -->
<div
    class="modal fade bd-example-modal-lg"
    id="editLessonModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
    >
        
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Lesson Details</h5>
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                    >
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="container" id="new-lesson-form">
                <form action="UpdateLesson" method="POST">
                    <div class="row mb-3">
                        <div class="col-8">
                            <p class="mb-1" style="font-weight: bold;">Lesson Name: (*)</p>
                            <input type="text" class="form-control" name="txtLessonName" value="${CURRENT_LESSON.name}" required/>
                        </div>

                        <div class="col-4">
                            <p class="mb-1" style="font-weight: bold;">Type: (*)</p>
                            <input type="text" class="form-control" name="txtLessonType" value="${CURRENT_LESSON.type}" readonly/>
                            <%--
                            <select class="form-select" id="type-options" name="txtLessonType" required>
                                <option hidden selected value="${CURRENT_LESSON.type}">${CURRENT_LESSON.type}</option>
                                <option value="" style="font-weight: bold;">Select a type</option> -->
                                <option value="Subject Topic">Subject Topic</option>
                                <option value="Lesson">Lesson</option>
                                <option value="Quiz">Quiz</option> 
                            </select>
                            --%>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-8">
                            <div id="update-select-topic">
                                <p class="mb-1" style="font-weight: bold;">Topic: (*)</p>
                                <select class="form-select" id="update-select-topic-dropdown" name="txtTopicID" required>
                                    <!-- <option value="" style="font-weight: bold;">Select a topic</option> -->
                                    <c:if test="${CURRENT_LESSON.topicID != 0}">                                        
                                        <c:forEach var="dto" items="${TOPICS_LIST}">
                                            <c:if test="${CURRENT_LESSON.topicID == dto.lessonID}">
                                                <option selected value="${dto.lessonID}">${dto.name}</option>
                                            </c:if>
                                            <c:if test="${CURRENT_LESSON.topicID != dto.lessonID}">
                                                <option value="${dto.lessonID}">${dto.name}</option>
                                            </c:if>                                                
                                        </c:forEach>                             
                                    </c:if>
                                    <c:if test="${CURRENT_LESSON.topicID == 0}">
                                        <option selected value="0">This is a topic.</option>
                                    </c:if>
                                </select>
                            </div>
                        </div>

                        <div class="col-4">
                            <p class="mb-1" style="font-weight: bold;">Order: (*)</p>
                            <input type="text" class="form-control" name="txtOrder" value="${CURRENT_LESSON.order}" required/>
                        </div>
                    </div>

                    <c:if test="${CURRENT_LESSON.type == 'Lesson'}">
                    <div class="mb-3" id="update-video-link">
                        <p class="mb-1" style="font-weight: bold;">Video Link:</p>
                        <input type="text" class="form-control" name="txtVideoLink" value="${CURRENT_LESSON.details.videoLink}"/>
                    </div>
                    </c:if>
                    
                    <c:if test="${CURRENT_LESSON.type != 'Quiz'}">
                    <div class="mb-3" id="update-html-content">
                        <p class="mb-1" style="font-weight: bold;">HTML Content: (*)</p>
                        <textarea class="form-control" placeholder="Content of a lesson." name="txtHtmlContent" required>${CURRENT_LESSON.details.htmlContent}</textarea>
                    </div>        
                    </c:if>
                    
                    <c:if test="${CURRENT_LESSON.type == 'Quiz'}">
                    <div class="mb-3" id="update-quizzes">
                        <p class="mb-1" style="font-weight: bold;">Quiz: (*)</p>                        
                        <select class="form-select" id="update-quiz-dropdown" name="txtQuizID" required>
                            <!-- <option value="" style="font-weight: bold;">Select a quiz</option> -->                            
                            <c:forEach var="dto" items="${QUIZZES_LIST}">
                                <c:if test="${CURRENT_LESSON.details.quizID == dto.quizID}">
                                    <option selected value="${dto.quizID}">${dto.name}</option> 
                                </c:if>
                                <c:if test="${CURRENT_LESSON.details.quizID != dto.quizID}">
                                    <option value="${dto.quizID}">${dto.name}</option> 
                                </c:if>                                
                            </c:forEach>                                                                    
                        </select>
                    </div>
                    </c:if>
                    
                    <div class="d-flex justify-content-end mt-4">                        
                        <!-- modal buttons -->
                        <div class="modal-footer">
                            <button
                                type="button"
                                class="btn btn-secondary"
                                data-dismiss="modal"
                                >
                                Back
                            </button>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
</div>
<!-- ===============================End Modal Lesson Details========================= -->


<!-- Static Table Start -->
<div class="data-table-area mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div
                class="col-lg-12 col-md-12 col-sm-12 col-xs-12 table responsive"
                >
                <div class="sparkline13-list table-wrapper">
                    <div class="sparkline13-hd">
                        <div class="main-sparkline13-hd col-lg-6">
                            <h2>
                                Manage <span class="table-project-n">Subject</span> Table
                            </h2>
                            <div class="col-sm-6" style="float: left">
                                <a
                                    href="#addNewSubjectModal"
                                    class="btn btn-success"
                                    data-toggle="modal"
                                    ><i class="material-icons">&#xE147;</i>
                                    <span>Add New Subject</span></a>

                            </div>
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
                                                <c:forEach items="${CATEGORY_LIST}" var="catelist">
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
                                                <a href="GetEditSubjectInfo?txtSubjectID=${list.subjectID}"> <button  style="width:100%" class="btn" type="submit">
                                                        Edit
                                                    </button></a>    
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
                                                <c:forEach items="${CATEGORY_LIST}" var="catelist">
                                                    <c:if test="${list.subjectCategoryID eq catelist.categoryID}">
                                                        ${catelist.categoryName}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${list.title}</td>
                                            <td>${list.thumbnail}</td>
                                            <td>${list.numOfLessons}</td>

                                            <td class="datatable-ct">${list.ownerID}</td>

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
                                                <a href="GetEditSubjectInfo?txtSubjectID=${list.subjectID}"> <button style="width:100%" class="btn" type="submit">
                                                        Edit
                                                    </button></a>    
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

<!-- ===============================Modal Quiz Details========================= -->
<div
    class="modal fade bd-example-modal-lg"
    id="quizDetailsModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
    >
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Quiz Details</h5>
                <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                    >
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <ul class="nav nav-tabs mb-3" id="quizTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button
                        class="nav-link active"
                        id="quiz-tab"
                        data-mdb-toggle="tab"
                        data-mdb-target="#quizOverview"
                        type="button"
                        role="tab"
                        aria-controls="home"
                        aria-selected="true"
                        >
                        Overview
                    </button>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent0">
                <form action="UpdateQuiz" method="post">
                    <input name="quizID" value="${QUIZ_INFO.quizID}" hidden/>
                    <input name="subjectID" value="${CURRENT_SUBJECT.subjectID}" hidden/>
                    <!--  Overview Form -->
                    <div
                        class="tab-pane fade show active container"
                        id="quizOverview"
                        role="tabpanel"
                        aria-labelledby="home-tab0"
                        >
                        <div class="form-group w-50" style="    margin-right: 10%;
                             display: inline-block;">
                            <label for="quizName">Name <span class="starcheck">*</span></label>
                            <input
                                required
                                name="name"
                                type="text"
                                class="form-control"
                                id="quizName"
                                value="${QUIZ_INFO.name}"
                                />
                        </div>
                        <div class="form-group w-30 " style="    display: inline-block;">
                            <label for="duration">Duration(in minutes) <span class="starcheck">*</span></label>
                            <input
                                required
                                name="duration"
                                type="number"
                                class="form-control"
                                id="duration"
                                value="${QUIZ_INFO.duration}"
                                min="1"
                                max="121    "
                                />

                        </div>
                        <div class="form-group w-50" style="    margin-right: 10%;
                             display: inline-block;">
                            <label for="lesson">lesson</label>
                            <select disabled name="lessonID" class="form-control" id="lesson">
                                <c:forEach items="${LEARN_LESSON}" var="list">
                                    <option value="${list.lessonID}" >${list.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group w-30 " style="display: inline-block;">
                            <label for="level">Quiz level</label>
                            <select disabled name="level" class="form-control" id="level">
                                <option selected hidden value="${QUIZ_INFO.level}">${QUIZ_INFO.level}</option>
                                <option value="easy">Easy</option>
                                <option value="medium">Medium</option>
                                <option value="hard">Hard</option>
                            </select>
                        </div>
                        <div class="form-group w-25 " style="display: inline-block;">
                            <label for="duration">number of questions </label>
                            <input
                                readonly
                                name="numOfQuestions"
                                type="number"
                                class="form-control"
                                id="duration"
                                placeholder=""
                                min="1"
                                max="121"
                                value="${QUIZ_INFO.numOfQuestions}"
                                />

                        </div>
                        <div class="form-group w-25 " style="display: inline-block;
                             margin-right: 10%;">
                            <label for="passrate">Pass rate(%) <span class="starcheck">*</span></label>
                            <input
                                required
                                name="passRate"
                                type="number"
                                class="form-control"
                                id="passrate"
                                value="${QUIZ_INFO.passRate}"/>
                        </div>
                    </div>
            </div>
            <!-- End Overview Form -->

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
            </div>
            <!--EnD modal buttons -->
            </form>
        </div>
    </div>
</div>
<!-- ===============================End Modal Quiz Details========================= -->

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

<script src="assets/js/AddNewLesson.js"></script>
    
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
<c:if test="${not empty CURRENT_SUBJECT}">
    <script>
        window.onload = function () {
            var button = document.getElementById("subjectDetailBtn");
            button.click();
        };
    </script>
</c:if>
<c:if test="${not empty CURRENT_PACKAGE}">
    <script>
        window.onload = function () {
            var button = document.getElementById("editPackageBtn");
            button.click();
        };
    </script>
</c:if>
<c:if test="${not empty CURRENT_LESSON}">
    <script>
        window.onload = function () {
            var button = document.getElementById("editLessonBtn");
            button.click();
        };
    </script>
</c:if>
<c:if test="${not empty QUIZ_INFO}">
    <script>
        window.onload = function () {
            var button = document.getElementById("editQuizBtn");
            button.click();
        };
    </script>
</c:if>        
</body>
</html>
