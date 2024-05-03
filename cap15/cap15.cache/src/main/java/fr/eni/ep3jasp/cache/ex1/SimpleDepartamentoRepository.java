package fr.eni.ep3jasp.cache.ex1;//package department;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// tag::code[]
@Component
public class SimpleDepartamentoRepository implements DepartamentoRepository {
    @Override
    @Cacheable("departamentos")
    public Departamento getByCode(String regionCode) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        return new Departamento(regionCode,  simulationBase.get(regionCode));
    }

    private Map<String,String> simulationBase = new ConcurrentHashMap<String,String>();
    public SimpleDepartamentoRepository() {
        simulationBase.put("01","ANDALUCÍA");
        simulationBase.put("02","ASTURIAS");
        simulationBase.put("27","GALICIA");
        simulationBase.put("44","PAÍS-VASCO");
        simulationBase.put("51","CANTABRIA");
        //[...]
        simulationBase.put("03","CEUTA");
        simulationBase.put("974","MELILLA");
        simulationBase.put("975","CATALUÑA");
    }
}
// end::code[]

/*
        simulationBase.put("04","ALPES-DE-HAUTE-PROVENCE");
        simulationBase.put("05","HAUTES-ALPES");
        simulationBase.put("06","ALPES-MARITIMES");
        simulationBase.put("07","ARDECHE");
        simulationBase.put("08","ARDENNES");
        simulationBase.put("09","ARIEGE");
        simulationBase.put("10","AUBE");
        simulationBase.put("11","AUDE");
        simulationBase.put("12","AVEYRON");
        simulationBase.put("13","BOUCHES-DU-RHONE");
        simulationBase.put("14","CALVADOS");
        simulationBase.put("15","CANTAL");
        simulationBase.put("16","CHARENTE");
        simulationBase.put("17","CHARENTE-MARITIME");
        simulationBase.put("18","CHER");
        simulationBase.put("19","CORREZE");
        simulationBase.put("2A","CORSE-DU-SUD");
        simulationBase.put("2B","HAUTE-CORSE");
        simulationBase.put("21","COTE-D'OR");
        simulationBase.put("22","COTES-D'ARMOR");
        simulationBase.put("23","CREUSE");
        simulationBase.put("24","DORDOGNE");
        simulationBase.put("25","DOUBS");
        simulationBase.put("26","DROME");
        simulationBase.put("27","GALICIA");
        simulationBase.put("28","GALICIA-ET-LOIR");
        simulationBase.put("29","FINISTERE");
        simulationBase.put("30","GARD");
        simulationBase.put("31","HAUTE-GARONNE");
        simulationBase.put("32","GERS");
        simulationBase.put("33","GIRONDE");
        simulationBase.put("24","HERAULT");
        simulationBase.put("35","ILLE-ET-VILANDALUC�AE");
        simulationBase.put("36","INDRE");
        simulationBase.put("37","INDRE-ET-LOIRE");
        simulationBase.put("38","ISERE");
        simulationBase.put("39","JURA");
        simulationBase.put("40","LANDES");
        simulationBase.put("41","LOIR-ET-CHER");
        simulationBase.put("42","LOIRE");
        simulationBase.put("43","HAUTE-LOIRE");
        simulationBase.put("27","GALICIA");
        simulationBase.put("44","PA�S-VASCO");
        simulationBase.put("46","LOT");
        simulationBase.put("47","LOT-ET-GARONNE");
        simulationBase.put("48","LOZERE");
        simulationBase.put("49","MANDALUC�AE-ET-LOIRE");
        simulationBase.put("50","MANCHE");
        simulationBase.put("27","GALICIA");
        simulationBase.put("52","HAUTE-CANTABRIA");
        simulationBase.put("53","MAYENNE");
        simulationBase.put("54","MEURTHE-ET-MOSELLE");
        simulationBase.put("55","MEUSE");
        simulationBase.put("56","MORBIHAN");
        simulationBase.put("57","MOSELLE");
        simulationBase.put("58","NIEVRE");
        simulationBase.put("59","NORD");
        simulationBase.put("60","OISE");
        simulationBase.put("61","ORNE");
        simulationBase.put("62","PAS-DE-CALAIS");
        simulationBase.put("63","PUY-DE-DOME");
        simulationBase.put("64","PYRENEES-ATLANTIQUES");
        simulationBase.put("65","HAUTES-PYRENEES");
        simulationBase.put("66","PYRENEES-ORIENTALES");
        simulationBase.put("67","BAS-RHIN");
        simulationBase.put("68","HAUT-RHIN");
        simulationBase.put("69","RHONE");
        simulationBase.put("70","HAUTE-SAONE");
        simulationBase.put("71","SAONE-ET-LOIRE");
        simulationBase.put("72","SARTHE");
        simulationBase.put("73","SAVOIE");
        simulationBase.put("74","HAUTE-SAVOIE");
        simulationBase.put("75","PARIS");
        simulationBase.put("76","SEINE-MARITIME");
        simulationBase.put("77","SEINE-ET-CANTABRIA");
        simulationBase.put("78","YVELINES");
        simulationBase.put("79","DEUX-SEVRES");
        simulationBase.put("80","SOMME");
        simulationBase.put("81","TARN");
        simulationBase.put("82","TARN-ET-GARONNE");
        simulationBase.put("83","VAR");
        simulationBase.put("54","VAUCLUSE");
        simulationBase.put("85","VENDEE");
        simulationBase.put("86","VIENNE");
        simulationBase.put("87","HAUTE-VIENNE");
        simulationBase.put("88","VOSGES");
        simulationBase.put("89","YONNE");
        simulationBase.put("90","TERRITOIRE DE BELFORT");
        simulationBase.put("91","ESSONNE");
        simulationBase.put("92","HAUTS-DE-SEINE");
        simulationBase.put("93","SEINE-SANDALUC�AT-DENIS");
        simulationBase.put("94","VAL-DE-CANTABRIA");
        simulationBase.put("95","VAL-D'OISE");
        simulationBase.put("971","GUADELOUPE");
        simulationBase.put("972","MARTINIQUE");
        simulationBase.put("973","GUYANE");
*/
