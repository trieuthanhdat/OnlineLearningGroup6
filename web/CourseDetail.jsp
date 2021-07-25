<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="overlay"></div>
<!-- ======= Pricing Section ======= -->
<c:if test="${empty OWNCOURSE }">


    <div class="pricing">
        <div class="container" id="pricing" data-aos="fade-up">
            <div class="row" style="display: -ms-flexbox;
                 display: flex;
                 -ms-flex-wrap: wrap;
                 flex-wrap: nowrap;
                 margin-right: -15px;
                 margin-left: -15px;
                 flex-direction: row;
                 align-content: stretch;
                 justify-content: space-between;">
                <c:forEach items="${CURRENT_SUBJECT.packages}" var="packages">
                    <div class="col-lg-3 col-md-6">
                        <div class="box">
                            <h3>${packages.name}</h3>
                            <c:if test="${packages.accessduration gt 0}" >
                                <c:set var="type" value="${packages.accessduration} month"></c:set>
                            </c:if>
                            <c:if test="${packages.accessduration eq 0}" >
                                <c:set var="type" value="unlimited"></c:set>
                                    <span class="advanced">Advanced</span>
                            </c:if>

                            <h4>
                                <sup>$</sup>${packages.salePrice}

                                <span> / ${type}</span>
                            </h4>
                            <ul style="min-height: 200px">
                                <li class="na">${packages.listPrice}$</li>
                                <li>${packages.description}</li>
                            </ul>
                            <div class="btn-wrap">

                                <a href="AddNewRegistration?txtSubjectID=${CURRENT_SUBJECT.subjectID}&txtPackageID=${packages.packageID}" class="btn-buy">Buy Now</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</c:if>
<!-- End Pricing Section -->

<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <div class="breadcrumbs" data-aos="fade-in" style="margin-top: 0;
         margin-top: 1px;
         min-height: 265px;
         background: black;">
        <div class="container">
            <div class="header-breadcumb">
                <div class="breadcrumb-wrap" style="background:none">
                    <div class="container">
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><b><a href="HomePage">Home</a></b></li>

                            <li class="breadcrumb-item"><b><a href="HomePage">nav</a></b></li>

                            <li class="breadcrumb-item"><b><a href="HomePage">Course Details</a></b></li>

                        </ul>
                    </div>
                </div>
            </div>
            <div class="body-breadcumb" style="display: flex;  flex-wrap: wrap;">
                <div class="col-lg-8">
                    <div class="body-breadcumb">
                        <h2 style="color: white">
                            <b>${CURRENT_SUBJECT.title}</b> 
                        </h2>
                    </div>
                    <div class="body-breadcumb-nav">
                        <div class="breadcumb-briefinfo" style="text-align: -webkit-auto;">
                            ${CURRENT_SUBJECT.briefInfo}.
                        </div>

                    </div>
                    <div class="body-breadcumb-nav" style="text-align: left;">
                        <p style="color: #169a16;">
                            ${REGIST_NUMBER} people enrolled in this course
                        </p>
                        <p style="color: white;">
                            Promoted by ${AUTHOR_NAME}  <i class="fas fa-user"></i>
                        </p>
                    </div>
                    <div class="body-breadcumb-nav" style="text-align: left;">

                        <c:if test="${empty OWNCOURSE}">

                            <input
                                type="button"
                                class="btn btn-primary btn-block"
                                id="enrollBtn"
                                value="Subcribe now"/>
                        </c:if>

                        <c:if test="${not empty OWNCOURSE}">

                            <a href="StartLearning?txtSubjectID=${CURRENT_SUBJECT.subjectID}"
                               type="button"
                               <button class="btn" style="background: #ff6f61; color:white">
                                    Study now
                                </button>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Breadcrumbs -->
    <!-- ======= Cource Details Section ======= -->
    <section id="course-details" class="course-details">
        <div class="container" data-aos="fade-up">
            <div class="row">
                <div class="col-lg-8">
                    <img
                        style="width: 730px;
                        height: 347px;
                        box-shadow: -1px 1px 10px #bba6a6;"
                        src="assets/img/${CURRENT_SUBJECT.thumbnail}"
                        class="img-fluid"
                        alt=""
                        />
                    <div class="tab-pane active show" id="tab-1">
                        <div class="row">
                            <div class="col-lg-8 details order-2 order-lg-1">
                                <h3>Description</h3>
                                <p style="text-align: justify;">
                                    ${CURRENT_SUBJECT_DETAIL.description}
                                </p>
                            </div>
                            <div class="col-lg-4 text-center order-1 order-lg-2">
                                <img
                                    style=" padding-top: 35px;"
                                    src="assets/img/course-details-tab-3.png"
                                    alt=""
                                    class="img-fluid"
                                    />
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="tab-2">
                        <div class="row">
                            <div class="col-lg-12 details order-2 order-lg-1">
                                <h3>Requirement</h3>
                                <p class="font-italic">
                                    Students should consider carefully:
                                </p>
                                <ul class="student-requirement">
                                    <li>Students will require an internet connection and PC or MAC Computer System.</li> 
                                    <li>Please note that all required downloads are free.</li> 
                                    <li>Text Editor such as Text Wrangler (MAC) or Notepad++ (Windows).</li> 
                                    <li>Students will require an internet connection and PC or MAC Computer System.</li> 
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="sidebar-enrollment">
                        <div
                            class="
                            course-info
                            d-flex
                            justify-content-between
                            align-items-center
                            "
                            >
                            <h5>Professor</h5>
                            <p><a href="#">(${AUTHOR_NAME})</a></p>
                        </div>

                        <div
                            class="
                            course-info
                            d-flex
                            justify-content-between
                            align-items-center
                            "
                            >
                            <div class="course-fee">
                                <h5>Course packages(VND)</h5>
                            </div>
                            <div class="fees">
                                <c:if test="${empty OWNCOURSE}">
                                    <c:forEach items="${CURRENT_SUBJECT.packages}" var="paList">
                                        <p>
                                            ${paList.salePrice}VND/${paList.accessduration} Tháng<br>
                                        </p>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty OWNCOURSE}">
                                    <c:forEach items="${CURRENT_SUBJECT.packages}" var="paList">
                                        <c:if test="${paList.packageID eq OWNCOURSE.packageID}">
                                            <p style="color:green">
                                                  ${paList.salePrice}/${paList.accessduration} Tháng<br>
                                              </p>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>

                    <div
                        class="
                        course-info
                        d-flex
                        justify-content-between
                        align-items-center
                        "
                        >
                        <h5>Available Seats</h5>
                        <p>${CURRENT_SUBJECT.numOfLessons}</p>
                    </div>
                    <div class="course-info d-flex align-items-center">
                        <c:if test="${empty OWNCOURSE }">

                            <input
                                type="button"
                                class="btn btn-primary btn-block"
                                id="enrollBtn"
                                value="Subcribe now"/>
                        </c:if>

                        <c:if test="${not empty OWNCOURSE}">

                            <a href="StartLearning?txtSubjectID=${CURRENT_SUBJECT.subjectID}"
                               type="button"
                               class="btn btn-primary btn-block"

                               value="Study now">Study now</a>
                        </c:if>
                    </div>
                    </di>
                </div>
            </div>
        </div>
        <!-- Dropdown content Section -->
        <section class="aos-init aos-animate" style="padding: 0;">
            <div class="row">
                <div class="middle col-lg-8">
                    <h3>Course Contents</h3>
                    <p>Total length: 10 hours</p>
                    <div id="accordion">
                        <c:forEach items="${TOPIC_LIST}" var="tpList" varStatus="count">
                            <div class="card">
                                <div class="card-header" id="heading${count.count}">
                                    <h5 class="mb-0">
                                        <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${count.count}" aria-expanded="true" aria-controls="collapse${count.count}">
                                            ${tpList.name}
                                        </button>
                                    </h5>
                                </div>
                                <div id="collapse${count.count}" class="collapse show" aria-labelledby="heading${count.count}" data-parent="#accordion">
                                    <c:forEach items="${SUBLESSON_LIST}" var="list">
                                        <div class="card-body">
                                            <li class="fas fa-book"></li> ${list.name}
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <!-- End  Dropdown content Section -->
        <!-- Student also enroll courses -->
        <section class="container aos-init aos-animate" style="    padding: 0;">
            <div class="sidebar-option col-lg-8" style="    padding: 0;">
                <div class="hardware-guides">
                    <div class="section-title" style="padding: 0;">
                        <h3>Student also enrolled</h3>
                    </div>
                    <c:forEach var="first" items="${MORE_SUBJECT}" end="3">
                        <a
                            data-toggle="popover"
                            data-placement="top"
                            title="Brief Info"
                            data-content="${first.briefInfo}"
                            href=""
                            class="trending-item pop project ${first.subjectCategoryID} MostEnrolled">
                            <div class="ti-pic">
                                <img src="assets/img/${first.thumbnail}" alt="" />
                            </div>
                            <div class="ti-text">
                                <h6>
                                    <div href="#">
                                        <b>${first.title}</b> 
                                    </div>
                                </h6>
                                <ul>
                                    <li>
                                        ${first.briefInfo}
                                    </li>
                                    <li>
                                        <span class="badge badge-success">New</span>
                                    </li>
                                </ul>

                                <ul>
                                    <li><span style="padding-right: 5px;"> Number of lessons: ${first.numOfLessons} </span></li>
                                </ul>
                            </div>
                        </a>
                    </c:forEach>
                    <!-- more items here -->
                    <div class="more-items smenu" id="more-items">
                        <c:forEach var="list" items="${MORE_SUBJECT}" begin="4" end="7">
                            <a
                                style="width:100%"
                                data-toggle="popover"
                                data-placement="top"
                                title="Brief Info"
                                data-content="${list.briefInfo}"
                                href=""
                                class="trending-item pop project ${list.subjectCategoryID} MostEnrolled">
                                <div class="ti-pic">
                                    <img src="assets/img/${list.thumbnail}" alt="" />
                                </div>
                                <div class="ti-text">
                                    <h6>
                                        <div href="#">
                                            <b>${list.title}</b> 
                                        </div>
                                    </h6>
                                    <ul>
                                        <li>
                                            ${list.briefInfo}
                                        </li>
                                        <li>
                                            <span class="badge badge-dark"> enrollment(s)</span>
                                        </li>
                                    </ul>

                                    <ul>
                                        <li><span style="padding-right: 5px;"> Number of lessons: ${list.numOfLessons} </span></li>
                                    </ul>
                                </div>
                            </a>
                        </c:forEach>
                        <!-- showmore btn -->
                    </div>
                    <div style="padding: 0;">
                        <li class="item" >
                            <a id="show-btn" class="btn" style="background: LAVENDER;">Continue</a>
                        </li>
                    </div>
                </div>
            </div>
        </section>
        <!-- Student also enroll courses -->
</section>
<!-- End Cource Details Section -->
</main>
<!-- End #main -->


<jsp:include page="WebFragment/footer.jsp"></jsp:include>
