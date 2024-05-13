package clase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.mql.MqlInteger;
import com.mongodb.client.model.mql.MqlInteger.*;
import com.mongodb.client.model.mql.MqlValues;
import org.bson.Document;

import java.time.LocalDate;
import java.util.List;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.mql.MqlValues.current;

public class Repaso {
    public static String cc = "mongodb://dam:java@localhost:27017/?authSource=admin&readPreference=primary&ssl=false&directConnection=true";

    public static void main(String[] args) {
        /*
        try(MongoClient c= MongoClients.create(cc)){
            System.out.println("Conectado al servidor");
            MongoDatabase db = c.getDatabase("alumnos");
            db.createCollection("vuelos");
            // Solo sale con document en el examen
            MongoCollection<Document> vuelos=db.getCollection("vuelos");
            // JSON
            Document d = new Document();
            d.append("nombre","Manuel")
                    .append("dni",new Document()
                            .append("numero",12345)
                            .append("letra","A")
                    ).append("edad",28)
                    .append("billetes", List.of(
                            new Document()
                                    .append("origen","Granada")
                                    .append("destino","Madrid")
                                    .append("fecha","8/3/2023 7:30")
                    ));
            vuelos.insertOne(d);
        }*/
        /*
        try (MongoClient c = MongoClients.create(cc)) {
            MongoDatabase db = c.getDatabase("alumnos");
            MongoCollection<Document> vuelos = db.getCollection("vuelos");*/
            /*
            Iterable<Document> resultado = vuelos.find(
                    lt("edad",30)
            ).projection(
                    fields(
                            include("nombre","edad"),
                            exclude("_id")
                    )
            );*/
            /*
            Iterable<Document> resultado = vuelos.find(
                    eq("billetes.0.origen","Granada")
            ).projection(
                fields(
                        include("nombre"),
                        exclude("_id")
                )
            );*/
            /*
            Iterable<Document> resultado = vuelos.find(
                    size("billetes",1)
            );*/
            /*
            Iterable<Document> resultado = vuelos.find(
                    elemMatch("billetes",eq("origen","Granada"))
            ).projection(
                    fields(
                            include("nombre"),
                            exclude("_id")
                    )
            );
            */
            /*
            Iterable<Document> resultado = vuelos.find(
                    or(
                            elemMatch("billetes",eq("origen","Madrid")),
                            elemMatch("billetes",eq("destino","Madrid"))
                    )
            ).projection(
                    fields(
                            include("nombre")
                    )
            );*/
            /*
            Iterable<Document> resultado = vuelos.find(
                    nor(
                            elemMatch("billetes",eq("origen","Madrid")),
                            elemMatch("billetes",eq("destino","Madrid"))
                    )
            ).projection(
                    fields(
                            include("nombre")
                    )
            );*/
            /*
            Iterable<Document> resultado = vuelos.find(
                    or(
                            eq("dni.letra","A"),
                            lt("dni.numero",5000)
                    )
            ).projection(
                    fields(
                            include("nombre"),
                            include("dni"),
                            include("edad")
                    )
            );*/
            /*
            Iterable<Document> resultado = vuelos.find(
                elemMatch("billetes",lt("fecha", LocalDate.of(2023,4,1)))
            );
            for(Document d:resultado){
                System.out.println(d);
            }*/
            /*
            Iterable<Document> resultado = vuelos.aggregate(List.of(
                            project(
                                    fields(
                                            computed("lineadni",current().getDocument("dni").getInteger("numero").asString()
                                                    .append(
                                                            MqlValues.of("-")
                                                    )
                                                    .append(
                                                    current().getDocument("dni").getString("letra")
                                            )),
                                            include("nombre", "edad"),
                                            exclude("_id")

                                    )
                            )
                    )
            );
            for(Document d:resultado){
                System.out.println(d);
            }
            */
            /*
            Iterable<Document> resultado = vuelos.aggregate(List.of(
                    match(
                            or(
                                    eq("billetes.origen","Granada"),
                                    eq("billetes.destino","Granada")
                            )
                    ),
                    group(
                            1, avg("media edad",current().getField("edad"))
                    )
            ));
            for(Document d:resultado){
                System.out.println(d);
            }
*/
            /*
            Iterable<Document> resultado = vuelos.aggregate(List.of(
                    unwind("$billetes"),
                    group(1, sum("pasajeros",1)
                    )
            ));
            for(Document d:resultado){
                System.out.println(d);
            }
        }*/
        try (MongoClient c = MongoClients.create(cc)) {
            MongoDatabase db = c.getDatabase("repaso");
            MongoCollection<Document> cine = db.getCollection("cine");
            /*
            Iterable<Document> resultado = cine.find(
                    in("proyecciones",  "Camelot")
            );
            for(Document d:resultado){
                System.out.println(d);
            }*/
            /*
            Iterable<Document> resultado = cine.aggregate(List.of(
                    match(
                            in("proyecciones",  "Camelot")
                    ),
                    project(
                            fields(
                                    computed("sala", MqlValues.of("sala ")
                                         .append(current().getInteger("_id").asString())),
                                    exclude("_id")
                            )
                    )

                )
            );
            for(Document d:resultado){
                System.out.println(d);
            }*/
            /*
            Iterable<Document> resultado = cine.aggregate(List.of(
                    match(
                            gt(
                                    divide("$capacidad", multiply("$ocupados",100))
                            )
                    ),
                    project(
                            include("_id")
                    )
            ));*/
            /*
            Iterable<Document> resultado = cine.aggregate(
                    List.of(
                            project(
                                    fields(
                                            computed(
                                                    "capacidadPorcentaje",current().getInteger("ocupados").multiply(100)
                                                            .divide(current().getInteger("capacidad"))
                                            ),
                                            include("_id")
                                    )
                            ),
                            match(
                                    gt("capacidadPorcentaje",50)
                            )
                    )
                    );
            for(Document d:resultado){
                System.out.println(d);
            }*/
            /*
            Iterable<Document> resultado = cine.find(
                    and(
                            in("proyecciones", "Hook"),
                            in("proyecciones", "Los Minions"),
                            eq("extras.imax", true)

                    )
            );
            for (Document d : resultado) {
                System.out.println(d);
            }*/
            Iterable<Document> resultado = cine.aggregate(List.of(

            ));
        }
    }
}
