import com.example.test.Accord
import com.example.test.R
import java.util.ArrayList

class ChordsUtils {


    companion object {
        public  fun generateChordList(): ArrayList<Accord> {

            val chordList = ArrayList<Accord>()

            chordList.add(Accord("A", R.drawable.a_major, arrayListOf<String>("Bm,D,E")))
            chordList.add(Accord("Am", R.drawable.a_minor, arrayListOf<String>("C,Dm,Em,F,G")))
            chordList.add(Accord("B", R.drawable.b_major, arrayListOf<String>("E")))
            chordList.add(Accord("Bm", R.drawable.b_minor, arrayListOf<String>("D,Em,G,A")))
            chordList.add(Accord("C", R.drawable.c_major, arrayListOf<String>("Dm,Em,F,G,Am")))
            chordList.add(Accord("Cm", R.drawable.c_minor, arrayListOf<String>("Cm,Fm,Gm")))
            chordList.add(Accord("D", R.drawable.d_major, arrayListOf<String>("Em,G,A,Bm")))
            chordList.add(Accord("Dm", R.drawable.d_minor, arrayListOf<String>("F,Gm,Am,C")))
            chordList.add(Accord("E", R.drawable.e_major, arrayListOf<String>("A,B")))
            chordList.add(Accord("Em", R.drawable.e_minor, arrayListOf<String>("G,Am,Bm,C,D")))
            chordList.add(Accord("F", R.drawable.f_major, arrayListOf<String>("Gm,Am,Cm,Dm")))
            chordList.add(Accord("Fm", R.drawable.f_minor, arrayListOf<String>("Cm")))
            chordList.add(Accord("G", R.drawable.g_major, arrayListOf<String>("Am,Bm,C,D,Em")))
            chordList.add(Accord("Gm", R.drawable.g_minor, arrayListOf<String>("Cm,Dm,F")))
            for (chord in chordList) {
                for (i in 0..chordList.size - 1) {
                    if (chordList[i].getGamme().contains(chord.getNom()) && !chord.getGamme()
                            .contains(chordList[i].getNom())
                    ) {
                        chord.addGamme(chordList[i].getNom())
                    }
                }

            }
            return chordList
        }
    }

}