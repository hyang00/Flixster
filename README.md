# Project 2 - Flixster

Flixster shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: 12 hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **scroll through current movies** from the Movie Database API
* [X] Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
* [X] For each movie displayed, user can see the following details:
  * [X] Title, Poster Image, Overview (Portrait mode)
  * [X] Title, Backdrop Image, Overview (Landscape mode)
* [X] Allow user to view details of the movie including ratings and popularity within a separate activity

The following **stretch** features are implemented:

* [X] Improved the user interface by experimenting with styling and coloring.
     - played around w/ text/background coloring
     - Added line limit for the movie descriptions
* [X] Apply rounded corners for the poster or background images using [Glide transformations](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#transformations)
* [X] Apply the popular [View Binding annotation library](http://guides.codepath.org/android/Reducing-View-Boilerplate-with-ViewBinding) to reduce boilerplate code.
* [X] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* [X] Added vote count to details page.
* [X] Added a play button to signal to the users whether or not the trailer is available to be played.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/hyang00/Flixster/blob/master/FlixsterSmallVert.gif' width='' alt='Video Walkthrough' />
<img src='https://github.com/hyang00/Flixster/blob/master/FlixsterSmallHorz.gif' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

I had an issue with making asynchronous HTTP requests. I forgot that they were asynchronous and code that needed the data from these requests was executed before the requests were performed, which resulted in my app crashing. I solved that by making the asynchronous HTTP request earlier. I still have questions about best practices surrounding asynchronous programming. Is there a certain amount of time we can expect these requests to be completed in or is there another way to safeguard against code executing before it has the data it needs.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android


