struct kompleks
    {
        jdouble realni;
        jdouble imaginarni;
        
        kompleks(){
            this->realni = 0;
            this->imaginarni = 0;
            }
        kompleks(jdouble realni, jdouble imaginarni){
            this->realni = realni;
            this->imaginarni = imaginarni;
        }
        
        kompleks zbroji(kompleks a, kompleks b){
            kompleks rez;
            rez.realni = a.realni + b.realni;
            rez.imaginarni = a.imaginarni + b.imaginarni;
            return rez;
        }
        
        kompleks pomnozi(kompleks a, kompleks b){
            kompleks rez;
            rez.realni = (a.realni * b.realni) - (a.imaginarni * b.imaginarni);
            rez.imaginarni = (a.realni*b.imaginarni) + (b.realni * a.imaginarni);
            return rez;        
        }
        kompleks neg (kompleks a)
        {
            return kompleks (-a.realni, -a.imaginarni);
        }

        kompleks oduzmi ( kompleks a, kompleks b){
            return zbroji(a, neg(b));        
        }

        kompleks absolute(kompleks a){
            return a.realni * a.realni + a.imaginarni * a.imaginarni;        
        }
        
    };


JNIEXPORT jint JNICALL Java_com_pmf_juliasetvisualizer_calculators_JuliaSetCalculator_calculate
  (JNIEnv *, jobject, jint quadrant, jdouble real, jdouble imaginary)
{
    
    kompleks c = (real, imaginary);

    jint iteracije = 100;
    jint max_br_iteracija;
    kompleks prijasnji, trenutni;
    jdouble magnituda = absolute(trenutni);
    for(max_br_iteracija = 0; i<iteracije && magnituda<4 ; i++)
    {
        trenutni = zbroji(pomnozi(prijasnji, prijasnji), c);
        prijasnji = trenutni;
    }

    return max_br_iteracija;
    
}

