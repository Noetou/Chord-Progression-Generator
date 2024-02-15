import com.example.test.Accord
import com.example.test.R
import java.util.ArrayList

class ChordsUtils {


    companion object {
        // create a list with all the chords and their related chords
        public  fun generateChordList(): ArrayList<Accord> {

            val chordList = ArrayList<Accord>()

            chordList.add(Accord("A", R.drawable.a, arrayListOf<String>("Bm","D","E","C#m","F#m","G#")))
            chordList.add(Accord("Am", R.drawable.a_minor, arrayListOf<String>("C","Dm","Em","F","G")))

            chordList.add(Accord("A#", R.drawable.b_flat, arrayListOf<String>("Cm","Dm","D#","F","Gm")))
            chordList.add(Accord("A#m", R.drawable.b_flat_minor, arrayListOf<String>("C#","D#m","Fm","F#","G#")))

            chordList.add(Accord("B", R.drawable.b, arrayListOf<String>("E","C#m","D#m","F#","G#m","A#")))
            chordList.add(Accord("Bm", R.drawable.b_minor, arrayListOf<String>("D","Em","G","A","C#","F#m")))

            chordList.add(Accord("C", R.drawable.c, arrayListOf<String>("Dm","Em","F","G","Am")))
            chordList.add(Accord("Cm", R.drawable.c_minor, arrayListOf<String>("Cm","Fm","Gm","G#","A#","D#")))

            chordList.add(Accord("C#", R.drawable.d_flat, arrayListOf<String>("D#m","Fm","F#","G#","A#m")))
            chordList.add(Accord("C#m", R.drawable.d_flat_minor, arrayListOf<String>("D#","E","F#m","G#m","A","B")))

            chordList.add(Accord("D", R.drawable.d, arrayListOf<String>("Em","G","A","Bm","F#m","C#")))
            chordList.add(Accord("Dm", R.drawable.d_minor, arrayListOf<String>("F","Gm","Am","C","A#")))

            chordList.add(Accord("D#", R.drawable.e_flat, arrayListOf<String>("Fm","Gm","G#","A#","Cm")))
            chordList.add(Accord("D#m", R.drawable.e_flat_minor, arrayListOf<String>("F#","G#m","A#m","B","C#")))

            chordList.add(Accord("E", R.drawable.e, arrayListOf<String>("A","B","F#m","G#m","C#m","D#")))
            chordList.add(Accord("Em", R.drawable.e_minor, arrayListOf<String>("G","Am","Bm","C","D","F#")))

            chordList.add(Accord("F", R.drawable.f, arrayListOf<String>("Gm","Am","Cm","Dm","A#")))
            chordList.add(Accord("Fm", R.drawable.f_minor, arrayListOf<String>("Cm","G#","A#m","C#","D#")))

            chordList.add(Accord("F#", R.drawable.g_flat, arrayListOf<String>("G#m","A#m","B","C#","D#m")))
            chordList.add(Accord("F#m", R.drawable.g_flat_minor, arrayListOf<String>("G#","A","Bm","C#m","D","E")))

            chordList.add(Accord("G", R.drawable.g, arrayListOf<String>("Am","Bm","C","D","Em","F#")))
            chordList.add(Accord("Gm", R.drawable.g_minor, arrayListOf<String>("Cm","Dm","F","A#","D#")))

            chordList.add(Accord("G#", R.drawable.a_flat, arrayListOf<String>("A#m","Cm","C#","D#","Fm")))
            chordList.add(Accord("G#m", R.drawable.a_flat_minor, arrayListOf<String>("A#","B","C#m","D#m","E","F#")))

            return chordList
        }
    }

}