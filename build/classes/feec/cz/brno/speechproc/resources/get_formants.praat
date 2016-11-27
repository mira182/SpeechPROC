#
# Praat formant and pitch extracter that draws burg and sl formant
# tracks in the Praat picture window.
# Author: Joseph Toscano <joseph-toscano@uiowa.edu>
#

form arguments
 word File
 word Output_Directory 
 positive Time_Step 0.005 
endform

Erase all

Read from file... 'file$'
object_name$ = selected$("Sound")
select Sound 'object_name$'
To Spectrogram... 'time_Step' 5000 0.002 20 Gaussian
Black
Viewport... 0 6 0 4
Paint... 0 0 0 0 100 yes 50 6 0 yes
Viewport... 0 6 4 8
Paint... 0 0 0 0 100 yes 50 6 0 yes

select Sound 'object_name$'
To Formant (burg)... 'time_Step' 5 5000 0.025 50
Write to short text file... 'output_Directory$''object_name$'-burg.Formant

Viewport... 0 6 0 4
select Formant 'object_name$'
Red
Speckle... 0 0 5000 30 yes

select Sound 'object_name$'
To Formant (sl)... 'time_Step' 5 5000 0.025 50
Write to short text file... 'output_Directory$''object_name$'-sl.Formant

Viewport... 0 6 4 8
select Formant 'object_name$'
Red
Speckle... 0 0 5000 30 yes

select Sound 'object_name$'
To Pitch... 'time_Step'  75 600
To Matrix
Write to short text file... 'output_Directory$''object_name$'.Matrix

clearinfo
print All done. The Praat picture window shows the burg (top) and sl (bottom)
print formant tracks. Remember to erase the Praat picture window when you're
print finished before running the script with another file.