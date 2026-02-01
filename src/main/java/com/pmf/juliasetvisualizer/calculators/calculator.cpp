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
  (JNIEnv *, jobject, jint maxIteracije, jdouble cReal, jdouble cImaginary, jdouble z0Real, jdouble z0Imaginary)
{
    
    kompleks c(cReal, cImaginary);

    jint brIteracija;
    kompleks prijasnji(z0Real,z0Imaginary), trenutni(0,0);
    jdouble magnituda = prijasnji.absolute();

    for(brIteracija = 0; brIteracija<maxIteracije && magnituda < 4 ; brIteracija++)
    {
        trenutni = prijasnji.pomnozi(prijasnji).zbroji(c);
        prijasnji = trenutni;
        magnituda = trenutni.absolute();
    }

    return brIteracija;
    
}

