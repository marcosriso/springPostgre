// Include gulp
var gulp = require('gulp');
 // Include plugins
var concat = require('gulp-concat');
 // Concatenate JS Files
gulp.task('scripts', function() {
	var src = ['bower_components/jquery/dist/jquery.min.js',
				'bower_components/bootstrap/dist/js/bootstrap.min.js'
				,'bower_components/angular/angular.min.js'];
    return gulp.src(src)
      .pipe(concat('main.js'))
      .pipe(gulp.dest('public/js'));
});

gulp.task('css', function() {
	var src = ['bower_components/bootstrap/dist/css/bootstrap.min.css',
		'bower_components/bootstrap/dist/css/bootstrap-theme.min.css',
		'custom/custom.css'];
    return gulp.src(src)
      .pipe(concat('main.css'))
      .pipe(gulp.dest('public/css'));
});

 // Default Task
gulp.task('default', ['scripts', 'css']);