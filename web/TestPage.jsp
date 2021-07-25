<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty CURRENT_QUIZ}">
    <!-- ==================================Modal take Exam==========================-->
    <div
        class="modal fade"
        id="TakeQuiz"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
        >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Take Exam??</h5>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                        >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-header" style="color: red">
                    ${CURRENT_QUIZ.name} || ${CURRENT_QUIZ.numOfQuestions} Questions
                </div>
                <div class="modal-body">
                    By clicking on the [Exit Exam] button below, you will complete your
                    current exam and receive your score. You will not be able to change
                    any answers after this point.
                    (Description)
                </div>
                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-dismiss="modal"
                        >
                        <-- Back
                    </button>
                    <a href="TakeQuiz?quizID=${CURRENT_QUIZ.quizID}" type="button" class="btn btn-primary">Take Quiz</a>
                </div>
            </div>
        </div>
    </div>
    <!--=================================End Modal take Exam=========================-->
</c:if>
<!-- ===============================Modal ReviewResult========================= -->
<div
    class="modal fade bd-example-modal-lg"
    id="ReviewResultModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="ReviewResultLabel"
    aria-hidden="true"
    >
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Review Result</h5>
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
                        type="button"
                        role="tab"
                        >
                        All questions
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button
                        class="nav-link"
                        id="profile-tab0"
                        type="button"
                        role="tab"
                        style="display: flex;
                        flex-direction: row;
                        flex-wrap: nowrap;
                        align-content: center;
                        justify-content: center;
                        align-items: flex-end;""
                        >
                        <li class="page-item"><a class="page-link" href="#"></a></li>

                        Unanswered
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button
                        class="nav-link"
                        id="profile-tab0"
                        type="button"
                        role="tab"
                        style="    display: flex;
                        flex-direction: row;
                        align-content: flex-end;
                        flex-wrap: nowrap;
                        justify-content: space-evenly;
                        align-items: flex-end;"
                        >
                        <li class="page-item">
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="16"
                                height="16"
                                fill="currentColor"
                                class="bi bi-tag"
                                viewBox="0 0 16 16"
                                style="position: relative; fill: whitesmoke"
                                >
                            <path
                                d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0z"
                                />
                            <path
                                d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1zm0 5.586 7 7L13.586 9l-7-7H2v4.586z"
                                />
                            </svg>
                        </li>
                        Marked
                    </button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent0" style="min-height: fit-content;">
                <!--  Overview Form -->
                <div class="col-lg-12"><span id="time">${CURRENT_QUIZ.duration}</span> minutes! 
                    <div style="text-align: end;"><button class="col-lg-3 btn" type="submit" class="btn btn-primary">Submit now</button></div>
                </div>

                <div
                    class="tab-pane fade show active container"
                    id="overview"
                    role="tabpanel"
                    aria-labelledby="home-tab0"
                    >
                    <ul class="pagination">



                        <svg
                            hidden
                            id="bookmark"
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-tag"
                            viewBox="0 0 16 16">
                        <path
                            d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0z" />
                        <path
                            d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1zm0 5.586 7 7L13.586 9l-7-7H2v4.586z"/></svg>
                        <c:forEach items="${QUIZ_QUESTION}" var="list" varStatus="count">
                            <li class="page-item">
                                <a
                                    class="page-link"
                                    href="#"
                                    data-slide-to="${count.count-1}"
                                    data-target="#carouselQuiz"
                                    >${count.count}</a
                                >
                            </li>
                        </c:forEach>
                        <li class="page-item">

                    </ul>

                    <div
                        id="carouselQuiz"
                        class="carousel slide"
                        data-ride="carousel"
                        >
                        <div class="carousel-inner" id="quiz1">
                            <form action="ShowResult" method="post">
                                <c:forEach items="${QUIZ_QUESTION}" var="question" varStatus="questionCounter">

                                    <div class="carousel-item 
                                         <c:if test="${questionCounter.first}">
                                             active 
                                         </c:if>
                                         ">
                                        <div class="col-12">
                                            <h5 class="fw-bold">
                                                ${question.content}
                                                <c:if test="${question.mediaLink}">
                                                    ${question.mediaLink}
                                                </c:if>
                                            </h5>
                                            <div>
                                                <c:forEach items="${QUIZ_QUESTION_OPTION}" var="option" varStatus="counter">
                                                    <c:if test="${option.questionNo == question.questionNo}">
                                                        <div class="form-check" style="padding: 1rem;">
                                                            <input class="form-check-input" type="radio" name="${question.questionNo}" id="option${counter.count}" value="${option.optionLetter}" checked>
                                                            <label class="form-check-label" for="option${counter.count}">
                                                                ${option.optionLetter}. ${option.content}
                                                            </label>
                                                        </div>
                                                        <input hidden name="${option.optionLetter}"></input>
                                                    </c:if>

                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                        </div>
                        </form>

                    </div>
                    <!-- End Overview Form -->
                </div>

                <!-- modal buttons -->
                <div class="modal-footer">
                    <button
                        class="carousel-control-prev btn previous-button"
                        href="#carouselQuiz"
                        role="button"
                        data-slide="prev"
                        value="previous"
                        style="position:relative; float:left"
                        >
                        <span class="">Previous</span>
                    </button>

                    <button
                        class="carousel-control-next btn next-button"
                        href="#carouselQuiz"
                        role="button"
                        data-slide="next"
                        value="next"
                        style="position:relative; float:right"
                        >
                        <span class="">Next</span>
                    </button>
                </div>
                <!--EnD modal buttons -->
            </div>
        </div>
    </div>
</div>
<!-- ===============================End Modal ReviewResult========================= -->


<!-- Lesson content section -->
<section class="lesson-content">

    <div class="" id="video-section" style="min-height: 720px;">
  <c:if test="${not empty CURRENT_QUIZ}">
        <div class="card container"style="    position: relative;
             margin-top: 50px;
             display: flex;
             min-width: 0;
             word-wrap: break-word;
             background-color: #ffffff08;
             background-clip: border-box;
             border: 1px solid rgba(0,0,0,.125);
             border-radius: .25rem;
             justify-content: space-around;
             align-items: stretch;
             align-content: center;
             flex-wrap: wrap;
             flex-direction: row;">
            <div class="card-header">
                <b style="color: green;
                   font-size: 25px;
                   font-style: oblique;">QUIZ</b>
            </div>
            <div class="card-body" >
                <h5 class="card-title"><b><h2>${CURRENT_QUIZ.name}</h2></b> <v>Duration: ${CURRENT_QUIZ.duration} minutes</v></h5>
                <div class="quiz-body">
                    <div class="card-text" style="display: flex;
                         padding: 20px 0;
                         flex-direction: row;
                         flex-wrap: wrap;
                         align-content: space-between;
                         justify-content: space-between;
                         border-bottom: 1px solid #80808036;">
                        <div class="quiz-info">
                            <div class="notify-text">
                                <b>Submit your assigment</b>
                            </div>
                            <div class="notify-text">
                                <div class="due-date" style="display: flex;
                                     flex-direction: row;
                                     justify-content: space-around;">
                                    <u>
                                        Due-date:  
                                    </u>
                                    <span>
                                        20/7/2021
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="quiz-btn" style="padding-right: 100px;">
                            <button  
                                type="button"
                                class="btn btn-primary"
                                data-toggle="modal"
                                data-target="#TakeQuiz" href="#" class="btn btn-primary">TAKE QUIZ</button>
                        </div>
                    </div>
                    <div class="card-text" style="    margin: 100px 100px 0;">
                        <div class="quiz-info" style="display: flex;
                             justify-content: space-between;
                             padding-bottom: 60px;">
                            <div class="notify-text" style="    display: flex;
                                 flex-direction: column;
                                 align-items: center;">
                                <b style="    border-bottom: 1px solid;
                                   padding-bottom: 10px;"> Receive Grade</b>
                                <div class="grading-text" style="font-size: larger;
                                     padding-top: 20px;">
                                    100%
                                </div>
                            </div>
                            <div class="notify-text">
                                <div class="due-date" style="display: flex;
                                     flex-direction: row;
                                     justify-content: space-around;">
                                    <span>
                                        <b>Score </b>${CURRENT_QUIZ.passRate}% or higher
                                    </span>
                                </div>
                            </div>

                        </div>

                        <div class="quiz-btn" style="padding-bottom: 20px;
                             display: flex;
                             align-content: end;
                             justify-content: flex-end;">
                            <button 
                                id="reviewResultBtn"
                                type="button"
                                class="btn btn-primary"
                                data-toggle="modal"
                                data-target="#ReviewResultModal"
                                >
                                Review Result 
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${not empty CURRENT_LESSON.details.videoLink}">
            <div id="player" class="embed-responsive embed-responsive-21by9 z-depth-1-half">
                <iframe 
                    class="embed-responsive-item"
                    src="${CURRENT_LESSON.details.videoLink}"
                    allowfullscreen
                    ></iframe>
            </div>
            <input value="${CURRENT_LESSON.details.videoLink}" id="video-id" type="hidden" hidden/>

        </c:if>
    </div>

    <div class="lesson-description">
        <div class="lesson-title  col-lg-9" style="    padding-left: 100px;"><h2>REVIEW ${CURRENT_LESSON.name}</h2></div>
        <ul class="nav nav-tabs">
            <li>
                <a data-toggle="tab" href="#search" class="active">Search</a>
            </li>
            <li id="anchor-content-tab">
                <a data-toggle="tab" href="#content" id="anchor-content">Content</a>
            </li>
            <li><a data-toggle="tab" href="#announcement">Announcement</a></li>
            <li><a data-toggle="tab" href="#overView">OverView</a></li>
        </ul>

        <div class="tab-content container">
            <div id="search" class="tab-pane fade in active show">
                <h3>Search</h3>
                <h4>Start a new search, lectures, lessons or resources</h4>
                <!-- Search Section -->
                <div class="input-group">
                    <input
                        type="text"
                        class="form-control"
                        placeholder="Search this blog"
                        />
                    <div class="input-group-append">
                        <button
                            class="btn btn-secondary"
                            type="button"
                            style="z-index: 0 !important"
                            >
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div id="content" class="tab-pane fade">
                <!-- Dropdown content Section -->

                <div class="dropdown-lessons menu1" id="dropdown-lesson">
                    <div class="w-100 aos-init aos-animate">
                        <div class="">
                            <div class="middle">
                                <h2>Course Contents</h2>
                                <p>Total length: 10 hours</p>
                                <div class="menu">
                                    <ul  style="background:none!important">
                                        <c:forEach items="${TOPICS_LIST}" var="tpList" varStatus="count">
                                            <li class="item cBtn" >
                                                <a class="btn">${tpList.name}</a>
                                                <div class="smenu">
                                                    <c:forEach items="${LESSONS_LIST}" var="list">
                                                        <c:if test="${tpList.lessonID eq list.topicID}">
                                                            <a href="StartLearning?txtSubjectID=${list.subjectID}&txtLessonID=${list.lessonID}">${list.name}</a>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </li>
                                        </c:forEach>
                                        <li class="item">
                                            <button href="#" id="collapseBtn" class="btn">
                                                Show All
                                            </button>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End  Dropdown content Section -->
            </div>
            <div id="announcement" class="tab-pane fade">
                <h3>Announcement: no announcement posted yet</h3>
                <p>
                    The instructor hasn?t added any announcements to this course
                    yet. Announcements are used to inform you of updates or
                    additions to the course.
                    Score: ${USER_SCORE.score}
                </p>
            </div>
            <div id="overView" class="tab-pane fade">
                <h3>OverView</h3>
                <p>
                    Description: Lorem ipsum dolor sit amet consectetur adipisicing
                    elit. Praesentium commodi amet blanditiis laborum reiciendis quo
                    id dolor ea iste aperiam, quae saepe ipsa libero veritatis?
                    Perferendis quidem inventore vero porro.
                </p>
            </div>
        </div>
    </div>
</div>
<!-- dropdown content  section-->

<div class="dropdown-content-lessons h100" id="dropdown-lesson-toggle">
    <div class="crossSymbol" style="    display: flex;
         justify-content: flex-end;
         transform: translateY(42px);">
        <a
            href="javascript:void(0)"
            class="closeBtn"
            id="closeBtn"
            onclick="closeNav()"
            >&times;</a
        >
    </div>
    <div class="w-100 aos-init aos-animate">
        <div class="">
            <div class="middle">
                <h2>Course Contents</h2>
                <p>Total length: 10 hours</p>
                <div class="menu">
                    <ul>
                        <!-- Changes made for disable quiz via tracking progress -->
                        <c:forEach items="${TOPICS_LIST}" var="tpList" varStatus="count">
                            <li class="item" id="${tpList.topicID}${count.count}">
                                <a href="#${tpList.topicID}${count.count}" class="btn">${tpList.name}</a>
                                <div class="smenu">
                                    <c:forEach items="${LESSONS_LIST}" var="list">
                                        <c:if test="${tpList.lessonID eq list.topicID}">
                                            <c:if test="${list.type == 'Lesson'}">
                                                <a href="StartLearning?txtSubjectID=${list.subjectID}&txtLessonID=${list.lessonID}">
                                                    ${list.name}
                                                </a>
                                            </c:if>                                                
                                            <c:if test="${list.type == 'Quiz'}">
                                                <c:forEach items="${ENABLED_QUIZZES}" var="quiz">
                                                    <c:if test="${list.lessonID == quiz.lessonID}">
                                                        <c:set var="status" value="ENABLED"/>
                                                    </c:if>                                                        
                                                </c:forEach>

                                                <c:if test="${not empty status}">
                                                    <a href="StartLearning?txtSubjectID=${list.subjectID}&txtLessonID=${list.lessonID}">
                                                        ${list.name}
                                                    </a>
                                                </c:if>

                                                <c:if test="${empty status}">
                                                    <a>${list.name} (You must complete previous quiz first!) </a>
                                                </c:if>
                                                <c:set var="status" value=""/>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </li>
                        </c:forEach>
                        <li class="item">
                            <a href="#" class="btn">collapse All</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- End  Dropdown content Section -->
<div class="dropdown-content-lessons">
    <button
        type="button"
        class="btn btn-default btn-arrow-left"
        id="slideInBtn"
        >
        Content
    </button>
</div>

</section>
<!-- End lesson content section -->

<!-- Youtube Player API -->
<script>
    // 2. This code loads the IFrame Player API code asynchronously.
    var tag = document.createElement("script");
    var videoID = document.getElementById("video-id");

    tag.src = "https://www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName("script")[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // 3. This function creates an <iframe> (and YouTube player)
    //    after the API code downloads.
    var player;
    function onYouTubeIframeAPIReady() {
        player = new YT.Player("player", {
            height: "720",
            width: "1280",
            videoId: videoID.value,
            playerVars: {
                playsinline: 1,
            },
            events: {
                onReady: onPlayerReady,
                onStateChange: onPlayerStateChange,
            },
        });
    }
    // 4. The API will call this function when the video player is ready.
    function onPlayerReady(event) {
        event.target.playVideo();
    }

    // 5. The API calls this function when the player's state changes.
    //    The function indicates that when playing a video (state=1),
    //    the player should play for six seconds and then stop.
    var done = false;
    function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.PLAYING && !done) {
            setTimeout(stopVideo, 6000);
            done = true;
        }
    }
    function stopVideo() {
        player.stopVideo();
    }
</script>
<script src="assets/js/timecounter.js"></script>
<script>
    function get_radio_values()
    {
        var form = document.getElementsByTagName('form');
        var radio_values = '';
        for (var i = 1; i < form.length + 1; i++)
        {
            var input = form[i - 1].getElementsByTagName('input');
            for (var j = 0; j < input.length; j++)
            {
                if (input[j].type == 'radio' && input[j].name == 'box' + i && input[j].checked)
                {
                    radio_values += 'box' + i + '=' + input[j].value + ',';
                }
            }
        }
        document.getElementById('result').innerHTML = radio_values;
    }
</script>
</script>
<script defer>
    $(document).ready(function () {
        var radios = document.getElementsByName("seconds");
        var val = localStorage.getItem('seconds');
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].value == val) {
                radios[i].checked = true;
            }
        }
        $('input[name="seconds"]').on('change', function () {
            localStorage.setItem('seconds', $(this).val());

        });
    });
    $(".carousel").carousel({
        interval: false,
    });
</script>
<jsp:include page="WebFragment/footer.jsp"></jsp:include>





<!-- Button trigger ReviewResult modal -->
<button 
    id="reviewResultBtn"
    type="button"
    class="btn btn-primary"
    data-toggle="modal"
    data-target="#ReviewResultModal"
    >
    Review Result 
</button>
<!-- Lesson content section -->
<section class="lesson-content">
    <div class="" id="video-section">
        <c:if test="${not empty CURRENT_QUIZ}">

            <div class="card container">
                <div class="card-header">
                    QUIZ: ${CURRENT_QUIZ.name}
                </div>
                <div class="card-body">
                    <h5 class="card-title">LEVEL: ${CURRENT_QUIZ.level}</h5>
                    <p class="card-text">
                    <ul>
                        <li>
                            PassRate: ${CURRENT_QUIZ.passRate}
                        </li>
                        <li>
                            Number of Questions: ${CURRENT_QUIZ.numOfQuestions}
                        </li>
                        <li>
                            Duration: ${CURRENT_QUIZ.duration} minutes
                        </li>
                    </ul>
                    </p>
                    <button  type="button"
                             class="btn btn-primary"
                             data-toggle="modal"
                             data-target="#TakeQuiz" href="#" class="btn btn-primary">TAKE QUIZ</a>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty CURRENT_LESSON.details.videoLink}">
            <div id="player" class="embed-responsive embed-responsive-21by9 z-depth-1-half">
                <iframe 
                    class="embed-responsive-item"
                    src="${CURRENT_LESSON.details.videoLink}"
                    allowfullscreen
                    ></iframe>
            </div>
            <input value="${CURRENT_LESSON.details.videoLink}" id="video-id" type="hidden" hidden/>

        </c:if>

        <div class="lesson-description">
            <h2>REVIEW ${CURRENT_LESSON.name}</h2>
            <ul class="nav nav-tabs">
                <li>
                    <a data-toggle="tab" href="#search" class="active">Search</a>
                </li>
                <li id="anchor-content-tab">
                    <a data-toggle="tab" href="#content" id="anchor-content">Content</a>
                </li>
                <li><a data-toggle="tab" href="#announcement">Announcement</a></li>
                <li><a data-toggle="tab" href="#overView">OverView</a></li>
            </ul>

            <div class="tab-content">
                <div id="search" class="tab-pane fade in active show">
                    <h3>Search</h3>
                    <h4>Start a new search, lectures, lessons or resources</h4>
                    <!-- Search Section -->
                    <div class="input-group">
                        <input
                            type="text"
                            class="form-control"
                            placeholder="Search this blog"
                            />
                        <div class="input-group-append">
                            <button
                                class="btn btn-secondary"
                                type="button"
                                style="z-index: 0 !important"
                                >
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div id="content" class="tab-pane fade">
                    <!-- Dropdown content Section -->

                    <div class="dropdown-lessons menu1" id="dropdown-lesson">
                        <div class="w-100 aos-init aos-animate">
                            <div class="">
                                <div class="middle">
                                    <h2>Course Contents</h2>
                                    <p>Total length: 10 hours</p>
                                    <div class="menu">
                                        <ul>
                                            <c:forEach items="${TOPICS_LIST}" var="tpList" varStatus="count">
                                                <li class="item cBtn" >
                                                    <a class="btn">${tpList.name}</a>
                                                    <div class="smenu">
                                                        <c:forEach items="${LESSONS_LIST}" var="list">
                                                            <c:if test="${tpList.lessonID eq list.topicID}">
                                                                <a href="StartLearning?txtSubjectID=${list.subjectID}&txtLessonID=${list.lessonID}">${list.name}</a>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                            <li class="item">
                                                <button href="#" id="collapseBtn" class="btn">
                                                    Show All
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End  Dropdown content Section -->
                </div>
                <div id="announcement" class="tab-pane fade">
                    <h3>Announcement: no announcement posted yet</h3>
                    <p>
                        The instructor hasn?t added any announcements to this course
                        yet. Announcements are used to inform you of updates or
                        additions to the course.
                        Score: ${USER_SCORE.score}
                    </p>
                </div>
                <div id="overView" class="tab-pane fade">
                    <h3>OverView</h3>
                    <p>
                        Description: Lorem ipsum dolor sit amet consectetur adipisicing
                        elit. Praesentium commodi amet blanditiis laborum reiciendis quo
                        id dolor ea iste aperiam, quae saepe ipsa libero veritatis?
                        Perferendis quidem inventore vero porro.
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- dropdown content  section-->

    <div class="dropdown-content-lessons h100" id="dropdown-lesson-toggle">
        <div class="crossSymbol" style="    display: flex;
             justify-content: flex-end;
             transform: translateY(42px);">
            <a
                href="javascript:void(0)"
                class="closeBtn"
                id="closeBtn"
                onclick="closeNav()"
                >&times;</a
            >
        </div>
        <div class="w-100 aos-init aos-animate">
            <div class="">
                <div class="middle">
                    <h2>Course Contents</h2>
                    <p>Total length: 10 hours</p>
                    <div class="menu">
                        <ul>
                            <!-- Changes made for disable quiz via tracking progress -->
                            <c:forEach items="${TOPICS_LIST}" var="tpList" varStatus="count">
                                <li class="item" id="${tpList.topicID}${count.count}">
                                    <a href="#${tpList.topicID}${count.count}" class="btn">${tpList.name}</a>
                                    <div class="smenu">
                                        <c:forEach items="${LESSONS_LIST}" var="list">
                                            <c:if test="${tpList.lessonID eq list.topicID}">
                                                <c:if test="${list.type == 'Lesson'}">
                                                    <a href="StartLearning?txtSubjectID=${list.subjectID}&txtLessonID=${list.lessonID}">
                                                        ${list.name}
                                                    </a>
                                                </c:if>                                                
                                                <c:if test="${list.type == 'Quiz'}">
                                                    <c:forEach items="${ENABLED_QUIZZES}" var="quiz">
                                                        <c:if test="${list.lessonID == quiz.lessonID}">
                                                            <c:set var="status" value="ENABLED"/>
                                                        </c:if>                                                        
                                                    </c:forEach>

                                                    <c:if test="${not empty status}">
                                                        <a href="StartLearning?txtSubjectID=${list.subjectID}&txtLessonID=${list.lessonID}">
                                                            ${list.name}
                                                        </a>
                                                    </c:if>

                                                    <c:if test="${empty status}">
                                                        <a>${list.name} (You must complete previous quiz first!) </a>
                                                    </c:if>
                                                    <c:set var="status" value=""/>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </li>
                            </c:forEach>
                            <li class="item">
                                <a href="#" class="btn">collapse All</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- End  Dropdown content Section -->
    <div class="dropdown-content-lessons">
        <button
            type="button"
            class="btn btn-default btn-arrow-left"
            id="slideInBtn"
            >
            Content
        </button>
    </div>

</section>
<!-- End lesson content section -->

<!-- Youtube Player API -->
<script>
    // 2. This code loads the IFrame Player API code asynchronously.
    var tag = document.createElement("script");
    var videoID = document.getElementById("video-id");

    tag.src = "https://www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName("script")[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // 3. This function creates an <iframe> (and YouTube player)
    //    after the API code downloads.
    var player;
    function onYouTubeIframeAPIReady() {
        player = new YT.Player("player", {
            height: "720",
            width: "1280",
            videoId: videoID.value,
            playerVars: {
                playsinline: 1,
            },
            events: {
                onReady: onPlayerReady,
                onStateChange: onPlayerStateChange,
            },
        });
    }
    // 4. The API will call this function when the video player is ready.
    function onPlayerReady(event) {
        event.target.playVideo();
    }

    // 5. The API calls this function when the player's state changes.
    //    The function indicates that when playing a video (state=1),
    //    the player should play for six seconds and then stop.
    var done = false;
    function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.PLAYING && !done) {
            setTimeout(stopVideo, 6000);
            done = true;
        }
    }
    function stopVideo() {
        player.stopVideo();
    }
</script>
<script src="assets/js/timecounter.js"></script>
<script>
    function get_radio_values()
    {
        var form = document.getElementsByTagName('form');
        var radio_values = '';
        for (var i = 1; i < form.length + 1; i++)
        {
            var input = form[i - 1].getElementsByTagName('input');
            for (var j = 0; j < input.length; j++)
            {
                if (input[j].type == 'radio' && input[j].name == 'box' + i && input[j].checked)
                {
                    radio_values += 'box' + i + '=' + input[j].value + ',';
                }
            }
        }
        document.getElementById('result').innerHTML = radio_values;
    }
</script>
</script>
<script defer>
    $(document).ready(function () {
        var radios = document.getElementsByName("seconds");
        var val = localStorage.getItem('seconds');
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].value == val) {
                radios[i].checked = true;
            }
        }
        $('input[name="seconds"]').on('change', function () {
            localStorage.setItem('seconds', $(this).val());

        });
    });
    $(".carousel").carousel({
        interval: false,
    });
</script>
<jsp:include page="WebFragment/footer.jsp"></jsp:include>