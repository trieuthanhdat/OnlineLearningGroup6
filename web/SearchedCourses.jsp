<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- =======  Filter Section ======= -->
<section class="container-fluid">
    <c:forEach items="${SEARCH_COURSES_LIST}"  var="list" varStatus="count">
        <c:set var="resultFound" value="${count.count}"/>
    </c:forEach>

    <div class="col-md-12 product-view">
        <div class="product-view-top search-result">
            <div class="row">
                <h2>${resultFound} results for "search string"</h2>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="product-notify">
                        <c:if test="${empty SEARCH_COURSES_LIST}">
                            <div class="alert alert-danger">
                                <strong>Nothing to show!</strong> Please search other courses.
                            </div>
                        </c:if>
                        <c:if test="${not empty SEARCH_COURSES_LIST }">

                            <div class="alert alert-success">
                                <strong>Success!</strong> Choose your course to enroll.
                            </div>
                        </c:if>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</section>
<!-- ======= END Filter Section ======= -->

<!--========== Searched courses =========-->
<section class="container-fluid aos-init aos-animate">
    <c:if test="${not empty SEARCH_COURSES_LIST}">
        <div class="row">
            <div class="col-lg-12 searched-courses">
                <div class="form-group searchbox-head">
                    <div class="filterBtn">
                        <div class="filter-popper">
                            <button class="btn" id="filterBtn">FILTER</button>
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="16"
                                height="16"
                                fill="currentColor"
                                class="bi bi-filter"
                                viewBox="0 0 16 16"
                                >
                                <path
                                    d="M6 10.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"
                                    />
                            </svg>
                        </div>
                    </div>

                    <select name="project" class="selectpicker form-control structure">
                        <option disabled>sort</option>
                        <option data-list="all" selected>all</option>
                        <c:forEach var="item" items="${CATEGORY_LIST}">
                            <option data-list="${item.categoryID}">${item.categoryName}</option>                    
                        </c:forEach>
                    </select>

                    <select name="service" class="form-control service">
                        <option disabled>sort</option>
                        <option data-list="all" selected>all</option>
                        <option data-list="MostEnrolled">Most Enrolled</option>
                        <option data-list="Newest">Newest</option>
                    </select>

                </div>
                <div class="result-found">
                    <span>
                        <p>${resultFound} results found</p>
                    </span>
                </div>
            </div>
        </div>
    </c:if>
    <div class="search-body" style="display: flex">
        <div id="accordion" class="myaccordion hidePanel">
            <div class="card">
                <div class="card-header" id="headingOne">
                    <h2 class="mb-0">
                        <button
                            class="
                            d-flex
                            align-items-center
                            justify-content-between
                            btn btn-link
                            collapsed
                            "
                            data-toggle="collapse"
                            data-target="#collapseOne"
                            aria-expanded="false"
                            aria-controls="collapseOne"
                            >
                            Features
                            <span class="fa-stack fa-sm">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fas fa-plus fa-stack-1x fa-inverse"></i>
                            </span>
                        </button>
                    </h2>
                </div>
                <div id="collapseOne" class="collapse" aria-labelledby="headingOne">
                    <div class="card-body">
                        <ul>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check1" checked>
                                        <label class="custom-control-label" for="check1">Subtitle</label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check2">
                                        <label class="custom-control-label" for="check2">Quizzes</label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check3" checked>
                                        <label class="custom-control-label" for="check3">Practice Tests</label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingTwo">
                    <h2 class="mb-0">
                        <button
                            class="
                            d-flex
                            align-items-center
                            justify-content-between
                            btn btn-link
                            collapsed
                            "
                            data-toggle="collapse"
                            data-target="#collapseTwo"
                            aria-expanded="false"
                            aria-controls="collapseTwo"
                            >
                            Price
                            <span class="fa-stack fa-2x">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fas fa-plus fa-stack-1x fa-inverse"></i>
                            </span>
                        </button>
                    </h2>
                </div>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo">
                    <div class="card-body">
                        <ul>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check4" checked>
                                        <label class="custom-control-label" for="check4">Free</label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check5">
                                        <label class="custom-control-label" for="check5">Under $20</label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check6" checked>
                                        <label class="custom-control-label" for="check6">Above $20</label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" id="headingThree">
                    <h2 class="mb-0">
                        <button
                            class="
                            d-flex
                            align-items-center
                            justify-content-between
                            btn btn-link
                            collapsed
                            "
                            data-toggle="collapse"
                            data-target="#collapseThree"
                            aria-expanded="false"
                            aria-controls="collapseThree"
                            >
                            Level
                            <span class="fa-stack fa-2x">
                                <i class="fas fa-circle fa-stack-2x"></i>
                                <i class="fas fa-plus fa-stack-1x fa-inverse"></i>
                            </span>
                        </button>
                    </h2>
                </div>
                <div
                    id="collapseThree"
                    class="collapse"
                    aria-labelledby="headingThree"
                    >
                    <div class="card-body">
                        <ul>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check7" checked>
                                        <label class="custom-control-label" for="check7">All</label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check8">
                                        <label class="custom-control-label" for="check8">Beginner</label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check9" checked>
                                        <label class="custom-control-label" for="check9">Intermediate</label>
                                </div>
                            </li>
                            <li class="list-group-item">
                                <!-- Default checked -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="check10" checked>
                                        <label class="custom-control-label" for="check10">Advanced</label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Accordion -->
        <div class="aos-init aos-animate col-lg-9" id="searched-content">
            <div class="sidebar-option">
                <div class="hardware-guides" id="searchcontent">
                    <c:forEach items="${SEARCH_COURSES_LIST}"  var="list">
                        <c:forEach items="${CATEGORY_LIST}" var="cList">
                            <c:if test="${cList.categoryID eq list.subjectCategoryID}">
                                <c:set var="categoryName" value="${cList.categoryName}"></c:set>
                            </c:if>
                        </c:forEach>
                        <a
                            data-toggle="popover"
                            data-placement="top"
                            title="Brief Info"
                            data-content="${list.briefInfo}"
                            href=""
                            class="trending-item pop project ${list.subjectCategoryID} MostEnrolled">
                            <div class="ti-pic">
                                <img src="assets/img/${list.thumbnail}" alt="" />
                            </div>
                            <div class="ti-text" style="display: flex;
                                 flex-direction: column;
                                 flex-wrap: nowrap;
                                 align-items: stretch;
                                 justify-content: flex-start;
                                 align-content: center;">
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
                                        <span class="badge badge-dark">${categoryName}</span>
                                    </li>
                                </ul>

                                <ul>
                                    <li><span style="padding-right: 5px;"> Number of lessons: ${list.numOfLessons} </span><span style="padding-left: 5px;border-left: 1px solid;">122 enrollment(s)</span></li>
                                    <li><i class="fas fa-clock"></i> total 10hrs</li>
                                </ul>
                            </div>
                        </a>
                    </c:forEach>
                </div>
                <!-- pagination -->
                <!--                <nav class="demo demo1" aria-label="Page navigation example">
                                    <ul class="pagination bootpag" id="page-selection">
                                        <li data-lp="1" class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li data-lp="1" class="page-item"><a class="page-link" href="javascript:void(0)">1</a></li>
                                        <li data-lp="2" class="page-item"><a class="page-link" href="javascript:void(0)">2</a></li>
                                        <li data-lp="3" class="page-item"><a class="page-link" href="javascript:void(0)">3</a></li>
                                        <li data-lp="1" class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>-->

            </div>
        </div>
    </div>
</section>
<!--==========END Searched courses =========-->


<jsp:include page="WebFragment/footer.jsp"></jsp:include>