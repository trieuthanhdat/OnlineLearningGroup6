var newTypesOption = document.getElementById('new-type-options');
var newVideoLink = document.getElementById('new-video-link');
var newHtmlContent = document.getElementById('new-html-content');
var newQuizzes = document.getElementById('new-quizzes');
var newQuizDropdown = document.getElementById('new-quiz-dropdown');
var newSelectTopic = document.getElementById('new-select-topic');
var newSelectTopicDropdown = document.getElementById('new-select-topic-dropdown');

newTypesOption.addEventListener('change', (event) => {
    if (newTypesOption.value === 'Subject Topic') {
        newSelectTopicDropdown.disabled = "true";
        newQuizDropdown.disabled = "true";
        newVideoLink.style.display = "none";
        newHtmlContent.style.display = "none";
        newQuizzes.style.display = "none";
    }
    else if (newTypesOption.value === 'Lesson') {
        newSelectTopicDropdown.removeAttribute("disabled");
        newQuizDropdown.disabled = "true";
        newVideoLink.style.display = "block";
        newHtmlContent.style.display = "block";
        newQuizzes.style.display = "none";
    } else if (newTypesOption.value === 'Quiz') {
        newSelectTopicDropdown.removeAttribute("disabled");
        newQuizDropdown.removeAttribute("disabled");
        newVideoLink.style.display = "none";
        newHtmlContent.style.display = "none";
        newQuizzes.style.display = "block";
    } else {
        newSelectTopicDropdown.removeAttribute("disabled");
        newQuizDropdown.removeAttribute("disabled");
        newVideoLink.style.display = "block";
        newHtmlContent.style.display = "block";
        newQuizzes.style.display = "block";
    }
});



