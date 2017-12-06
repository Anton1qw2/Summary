package BussinessLogic;

import DatabasePrimitive.DatabaseWork;
import DatabasePrimitive.DatabaseWorkNet;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.sql.SQLSyntaxErrorException;

public class ConsoleLogic implements Logic {
    private DatabaseWork db;

    public ConsoleLogic(DatabaseWorkNet db) {
        this.db = db;
    }

    public DatabaseWork getDb() {
        return db;
    }

    public void setDb(DatabaseWork db) {
        this.db = db;
    }

    public void startQuery(String selection) {
        String h[] = selection.split(" ");
        try {
            switch (h[0]) {
                case "RackInfo":
                    System.out.println("Данные о Rack'e c ID " + h[1] + ": ");
                    try {
                        System.out.println(db.find(Integer.parseInt(h[1])).values());
                    } catch (EmptyResultDataAccessException e) {
                        System.out.println("Не найдены");
                    }
                    break;
                case "findObjectType":
                    System.out.println("Список объектных типов:");
                    db.findP( );
                    break;
                case "findObjects":
                    db.findObjects();
                    break;
                case "ObjectInfo":
                    db.objectInfo(Integer.parseInt(h[1]));
                    break;
                case "add":
                    db.add(h[1]);
                    break;
                case "addObject":
                    db.addSint( Integer.parseInt(h[1]));
                    System.out.println("Объекты созданы");
                    break;
                case "addParams":
                    db.addParams();
                    System.out.println("Созданы параметры");
                    break;
                case "help":
                    System.out.println(
                            "Коммнада addObject + целочисленный параметр создаёт n объектов каждого типа" +
                                    "\n" +
                                    "Команда addParams создает связи атрибутов и объектов в таблице параметров\n" +
                                    "\n" +
//                                    "Команда RackInfo + id выводит информацию о наличии" +
//                                    "свобобных и занятых мест в реке с данным id.\n" +
//                                    "\n" +
                                    "Команда findObjectType выводит список объектных типов\n" +
                                    "\n" +
                                    "Команта findObjects выводит список всех объектов\n" +

                                    "\n" +
                                    "Команда ObjectInfo + id выводит информацию об объекте\n"
                                     +
                                    "\n"+
                                    "Команда add + тип объекта создает объкт данного типа с ручным вводом параметров\n " +
                                    "\n" );
                    break;
                default:
                    System.out.println("Неизвестная команда");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.print("Неверный формат команды");
        }catch (NumberFormatException e){
            System.out.print("Некорректный ввод числового параметра");
        }
    }
}
