<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- ======= Breadcrumbs ======= -->
<div class="breadcrumb-wrap">
    <div class="container">
        <ul class="breadcrumb">
            <li class="breadcrumb-item"><a href="HomePage">Home</a></li>
            <li class="breadcrumb-item active">My courses</li>
        </ul>
    </div>
</div>
<div class="breadcrumbs" data-aos="fade-in" style="    margin-top: 0;
     background: black;">
    <div class="container">
        <h2 style="color:white">My Courses</h2>
        <p style="color: white">
            This thing will be done perfectly
        </p>
    </div>
</div>
<!-- End Breadcrumbs -->
<div class="container-fluid mycourse">

    <div class="row">
        <div class="col-md-3">

            <div class="sidebar">

                <div class="sidebar-widget">
                    <div class="image">
                        <a href="#"
                           ><img src="assets/img/ads-2.jpg" alt="Image"
                              /></a>
                    </div>
                </div>

                <div class="sidebar-widget">
                    <div class="tab-news">
                        <ul class="nav nav-pills nav-justified">
                            <li class="nav-item">
                                <a
                                    class="nav-link active"
                                    data-toggle="pill"
                                    href="#featured"
                                    >Featured</a
                                >
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" data-toggle="pill" href="#latest"
                                   >Latest</a
                                >
                            </li>
                        </ul>

                        <div class="tab-content" style="min-height:fit-content">
                            <div id="featured" class="container tab-pane active">
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-1.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href="Lectures.jsp"
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-2.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-5.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                            </div>
                            <div id="popular" class="container tab-pane fade">
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-2.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-1.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-2.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                            </div>
                            <div id="latest" class="container tab-pane fade">
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-5.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- category bar end -->

        <!-- =======  Courses Section ======= -->
        <div class="col-md-9 product-view">
            <div class="product-view-top" style="border-bottom: 1px solid lightcoral;background: none;
                 padding-bottom: 0;">
                <div class="row" style="padding-left:8%">
                    <div class="col-md-3">
                        <div class="product-search">
                            <input type="email" placeholder="Search">
                            <button><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                    <div  style="width: 20%">
                        <div class="product-short">
                            <div class="dropdown">
                                <div class="dropdown-toggle" data-toggle="dropdown">Product short by</div>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a href="#" class="dropdown-item">Newest</a>
                                    <a href="#" class="dropdown-item">Popular</a>
                                    <a href="#" class="dropdown-item">Most sale</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div  style="width: 18%">
                        <div class="product-price-range">
                            <div class="dropdown">
                                <div class="dropdown-toggle" data-toggle="dropdown">Instructor</div>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a href="#" class="dropdown-item">$0 to $50</a>
                                    <a href="#" class="dropdown-item">$51 to $100</a>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div  style="width: 18%">
                        <div class="product-price-range">
                            <div class="dropdown">
                                <div class="dropdown-toggle" data-toggle="dropdown">Category</div>
                                <div class="dropdown-menu dropdown-menu-right">

                                    <ul>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-home"></i>Categories</a
                                            >
                                        </li>
                                        <li>
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-shopping-bag"></i>Business</a
                                            >
                                        </li>
                                        <li>
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-plus-square"></i>Software Engineering</a
                                            >
                                        </li>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-female"></i>Art</a
                                            >
                                        </li>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-child"></i>Graphic design</a
                                            >
                                        </li>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-tshirt"></i>Biology</a
                                            >
                                        </li>
                                        <li>
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-mobile-alt"></i>Physic</a
                                            >
                                        </li>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-microchip"></i>Mathemetics</a
                                            >
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <section id="popular-courses" class="courses" style="padding-top: 0;">
                <div class="container" data-aos="fade-up">
                    <div class="row" data-aos="zoom-in" data-aos-delay="100">
                        <c:forEach items="${MY_COURSES}" var="list">

                            <div class="col-lg-3 col-md-4" style="max-width: 215px;
                                 margin: 0 1% 1% 0;
                                 padding: 0;
                                 box-shadow: 0px 5px 4px 0px #d0cece;
                                 max-height: 335px;">
                                <div class="course-item" style="border:none">
                                    <a href="#"><img src="assets/img/sub1.jpg" class="img-fluid" alt="..." /></a>
                                    <div class="course-content">
                                        <div
                                            <c:forEach items="${sessionScope.CATEGORY_LIST}" var="catelist" >
                                                <c:if test="${list.subjectCategoryID eq catelist.categoryID}">
                                                    <c:set value="${catelist.categoryName}" var="cate"></c:set>
                                                </c:if>
                                            </c:forEach>
                                            <h4>
                                                ${cate}
                                            </h4>
                                        </div>
                                        <h3><a href="StartLearning?txtSubjectID=${list.subjectID}">${list.title}</a></h3>
                                        <p style="    overflow: hidden;
                                           min-height: 60px;">
                                            ${list.briefInfo}.
                                        </p>
                                        <div class="progress">
                                            <c:forEach items="${PROGRESS_LIST}" var="progress">
                                                <c:if test="${progress.subjectID eq list.subjectID}">
                                                    <div class="progress-bar" role="progressbar" style="width: ${(progress.numOfCompleteLessonQuiz/progress.numberOfLessonQuiz)*100}%;" aria-valuenow="${progress.numOfCompleteLessonQuiz}" aria-valuemin="0" aria-valuemax="${progress.numberOfLessonQuiz}">${(progress.numOfCompleteLessonQuiz/progress.numberOfLessonQuiz)*100}%</div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- pagination -->
                    <nav class="demo demo1" aria-label="Page navigation example" style="display: flex;
                         flex-direction: row;
                         flex-wrap: nowrap;
                         align-content: center;
                         justify-content: space-around;">
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
                    </nav>
                </div>

            </section>
        </div>
    </div>
</div>
<!-- End  Courses Section -->
<jsp:include page="WebFragment/footer.jsp"></jsp:include>