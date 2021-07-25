<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
<jsp:include page="WebFragment/PopUpSignInRegister.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty LOGIN_ERROR}">
    <script>
        window.onload = function () {
            var button = document.getElementById("sign-in");
            button.click();
        };
    </script>
</c:if>
<!-- Start Slider Area -->
<div class="slider-area">
    <div class="container-fluid p-0">
        <div class="row no-gutters">
            <div class="col-12">
                <div class="slider-carousel owl-carousel">
                    <c:if test="${empty sessionScope.SUBJECT_LIST}">EMPTY</c:if>
                    <c:if test="${empty sessionScope.CATEGORY_LIST}">EMPTY</c:if>
                        <div class="single-slider slider-bg-1 text-center">
                            <div class="slider-inner">
                                <h1>Welcome to Online learning</h1>
                                <h5>
                                    I learn - I study
                                </h5>
                                <a class="btn get-started-btn" href="">Get Started</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Slider Area -->

    <!-- Start Hire Us Area -->
    <div class="hire-us-area theme-bg js--sticky-menu">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 col-md-9 col-12">
                    <div class="hire-us-content">
                        <h6>
                            We are a team of Promising people with <span>100+</span> percent of strenght?
                        </h6>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 offset-lg-2 col-12 text-right">
                    <a class="btn btn-primary" href="#">Hire Us</a>
                </div>
            </div>
        </div>
    </div>
    <!-- End Hire Us Area -->

    <!-- Category News Start-->
    <div class="cat-news">
        <div class="container">
            <div class="row">
            <c:forEach items="${sessionScope.CATEGORY_LIST}" var="catelist">
                <div class="col-md-6">
                    <h2>${catelist.categoryName}</h2>
                    <div class="row cn-slider">
                        <c:forEach items="${sessionScope.SUBJECT_LIST}" var="list" >
                            <c:if test="${list.subjectCategoryID eq catelist.categoryID}">
                                <div class="col-md-6">
                                    <div class="cn-img">
                                        <img src="assets/img/${list.getThumbnail()}" />
                                        <div class="cn-title">
                                            <a href="CourseDetails?txtSubjectID=${list.subjectID}">${list.title}</a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
<!-- Category News End-->

<!-- Tab News Start-->
<div class="tab-news">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <ul class="nav nav-pills nav-justified">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="pill" href="#featured"
                           >Featured Posts</a
                        >
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="featured" class="container tab-pane active">
                        <c:forEach items="${FEATURED_POSTS}" var="list" begin="0" end="3">
                            <div class="tn-news">
                                <div class="tn-img">
                                    <img src="assets/img/${list.thumbnail}" />
                                </div>
                                <div class="tn-title">
                                    <a href="PostDetails?txtPostID=${list.postID}">${list.title}</a>
                                </div>
                            </div>
                        </c:forEach>

                    </div>



                </div>
            </div>

            <div class="col-md-6">
                <ul class="nav nav-pills nav-justified">
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="pill" href="#latest"
                           >Latest Posts</a
                        >
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="latest" class="container tab-pane active">
                        <c:forEach items="${RECENT_POSTS}" var="list" begin="0" end="3">
                            <div class="tn-news">
                                <div class="tn-img">
                                    <img src="assets/img/${list.thumbnail}" />
                                </div>
                                <div class="tn-title">
                                    <a href="PostDetails?txtPostID=${list.postID}">${list.title}</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Tab News Start-->

<!-- Start Working With Us Area -->
<div
    class="working-with-us-area gray-bg"
    style="
    margin-bottom: 4%;
    background-image: url(assets/img/@PhamQuangLinh.JPG);
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: 100% 100%;
    "
    >
    <div class="container">
        <div class="row">
            <div
                class="col-md-6 col-12 text-center d-flex align-items-center"
                style="color: whitesmoke"
                >
                <div class="hire-us-content">
                    <div class="section-title">
                        <h4>Working With Us</h4>
                    </div>
                    <p>
                        We are team of <span>100%</span>concentration and responsible
                    </p>
                    <a href="#" class="hire-us-btn">Hire Us</a>
                </div>
            </div>
            <div class="col-md-6 d-none d-md-block">
                <div class="hire-us-img">
                    <div src="" alt="" style="width: 430px; height: 580px"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Working With Us Area -->

<!-- Main News Start-->
<div class="main-news">
    <div class="container">
        <div class="row">
            <div class="col-lg-9">
                <div class="row">
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-1.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-2.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-3.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-4.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-5.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-1.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-2.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-3.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mn-img">
                            <img src="assets/img/news-350x223-4.jpg" />
                            <div class="mn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-3">
                <div class="mn-list">
                    <h2>Read More</h2>
                    <ul>
                        <li><a href="">Lorem ipsum dolor sit amet</a></li>
                        <li><a href="">Pellentesque tincidunt enim libero</a></li>
                        <li><a href="">Morbi id finibus diam vel pretium enim</a></li>
                        <li>
                            <a href="">Duis semper sapien in eros euismod sodales</a>
                        </li>
                        <li><a href="">Vestibulum cursus lorem nibh</a></li>
                        <li>
                            <a href="">Morbi ullamcorper vulputate metus non eleifend</a>
                        </li>
                        <li><a href="">Etiam vitae elit felis sit amet</a></li>
                        <li><a href="">Nullam congue massa vitae quam</a></li>
                        <li><a href="">Proin sed ante rutrum</a></li>
                        <li><a href="">Curabitur vel lectus</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Main News End-->

<!-- ======= Testimonials Section ======= -->
<section id="testimonials" class="testimonials">
    <div class="container-fluid" data-aos="zoom-in">

        <div class="owl-carousel testimonials-carousel">

            <div class="testimonial-item">
                <img src="assets/img/team/thanhdat.jpg" class="testimonial-img" alt="">
                <h3>Thanh Dat</h3>
                <h4>Front-end developer</h4>
                <p>
                    <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                    Front-end dev role: a very warm man pursuing Game industry  
                    <i class="bx bxs-quote-alt-right quote-icon-right"></i>
                </p>
            </div>

            <div class="testimonial-item">
                <img src="assets/img/team/hoangpho.jpg" class="testimonial-img" alt="">
                <h3>Hoang Pho</h3>
                <h4>Back-end developer</h4>
                <p>
                    <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                    Back-end dev role: with nickname NemuTheLost-mr.City, Good man enough to make girls fall in love
                    <i class="bx bxs-quote-alt-right quote-icon-right"></i>
                </p>
            </div>

            <div class="testimonial-item">
                <img src="assets/img/testimonials/testimonials-3.jpg" class="testimonial-img" alt="">
                <h3>Minh Khoa</h3>
                <h4>Back-end developer</h4>
                <p>
                    <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                    Back-end dev role: "The lost mind among big people"
                    <i class="bx bxs-quote-alt-right quote-icon-right"></i>
                </p>
            </div>

            <div class="testimonial-item">
                <img src="assets/img/team/nguyenvinh.jpg" class="testimonial-img" alt="">
                <h3>Nguyen Vinh</h3>
                <h4>Back-end developer</h4>
                <p>
                    <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                    Back-end dev role: "think big move big"
                    <i class="bx bxs-quote-alt-right quote-icon-right"></i>
                </p>
            </div>



        </div>

    </div>
</section><!-- End Testimonials Section -->

<!-- Start Why Choose Us Area -->
<div class="choose-us-area pt-70 pb-70" id="page">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center mb-70">
                <div class="section-title">
                    <h4 style="color: black">Why Choose Us</h4>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="video-img-thumbnail text-center">
                    <a
                        class="video-play"
                        href="https://www.youtube.com/watch?v=SGo1i4BEBL4"
                        ><i class="far fa-play-circle"></i
                        ></a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="single-choose-item">
                    <h6>
                        <a href="#"><i class="fas fa-cogs"></i> Willing to work</a>
                    </h6>
                    <p>
                        Morbi vehicula a nibh in commodo. Aliquam quis dolor eget lectus
                        pulvinar malesuada. Suspendisse eu rhoncus ligula.
                    </p>
                </div>
                <div class="single-choose-item">
                    <h6>
                        <a href="#"><i class="fas fa-gem"></i> Sociable team</a>
                    </h6>
                    <p>
                        Nam orci metus, varius at nisl at, tempor facilisis magna. Ut
                        maximus felis et tincidunt lacinia. Nulla malesuada ipsum at
                        magna condimentum pharetra.
                    </p>
                </div>
                <div class="single-choose-item">
                    <h6>
                        <a href="#"><i class="fas fa-briefcase"></i> Well prepared</a>
                    </h6>
                    <p>
                        Fusce viverra risus diam, in luctus nulla porta vel. Etiam nunc
                        lorem, dapibus id augue vitae, lacinia pharetra eros. Fusce ac
                        egestas purus, non porta est.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Why Choose Us Area -->

<jsp:include page="WebFragment/footer.jsp"></jsp:include>