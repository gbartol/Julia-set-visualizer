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
        
        kompleks zbroji(const kompleks &b) const {
            kompleks rez;
            rez.realni = realni + b.realni;
            rez.imaginarni = imaginarni + b.imaginarni;
            return rez;
        }
        
        kompleks pomnozi(const kompleks &b) const {
            kompleks rez;
            rez.realni = (realni * b.realni) - (imaginarni * b.imaginarni);
            rez.imaginarni = (realni*b.imaginarni) + (b.realni * imaginarni);
            return rez;        
        }

        jdouble absolute() const{
            return realni * realni + imaginarni * imaginarni;        
        }
        
    };


JNIEXPORT jint JNICALL Java_com_pmf_juliasetvisualizer_calculators_JuliaSetCalculator_calculate
  (JNIEnv *, jobject, jint quadrant, jdouble real, jdouble imaginary)
{
    
    kompleks c(real, imaginary);

    jint iteracije = 100;
    jint max_br_iteracija;
    kompleks prijasnji(0,0), trenutni(0,0);
    jdouble magnituda = trenutni.absolute();
    for(max_br_iteracija = 0; max_br_iteracija<iteracije && magnituda < 4 ; max_br_iteracija++)
    {
        trenutni = prijasnji.pomnozi(prijasnji).zbroji(c);
        prijasnji = trenutni;
        magnituda = trenutni.absolute();
    }

    return max_br_iteracija;
    
}

