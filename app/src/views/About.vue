<template>
  <div class="about">
    <h1>Code and Framework Decisions</h1>
    <p>
      Unfortunately, I did not get the solution fully working before hitting the time limit. I ran into an issue binding
      the response from the Spring Boot application to the proper Vue model. I choose to try Vue for the front end
      despite being previously unfamiliar with it after reviewing a couple of frameworks, including Clojurescript. Vue
      appeared to have a nice mix of ease-of-setup and low boilerplate code, while being similar in design principals to
      what I was used to with Angular, that I thought might enable me to get more done quickly after I looked over a few
      brief tutorials.
    </p>
    <p>
      For the backend I stuck with Spring Boot and Java since I was already taking a risk by trying a new UI framework.
      Development on that portion went fairly quickly although it is missing many features that would be required for a
      production application like authentication/authorization, proper error handling, and unit tests.
    </p>
  </div>
</template>
