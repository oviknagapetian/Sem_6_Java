package org.example;

import java.util.*;

public class Ноутбук {
    private String модель;
    private int объемОЗУ;
    private int объемЖД;
    private String операционнаяСистема;
    private String цвет;

    // Конструктор класса Ноутбук
    public Ноутбук(String модель, int объемОЗУ, int объемЖД, String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.объемОЗУ = объемОЗУ;
        this.объемЖД = объемЖД;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    // Геттеры и сеттеры для полей класса Ноутбук

    public String getМодель() {
        return модель;
    }

    public void setМодель(String модель) {
        this.модель = модель;
    }

    public int getОбъемОЗУ() {
        return объемОЗУ;
    }

    public void setОбъемОЗУ(int объемОЗУ) {
        this.объемОЗУ = объемОЗУ;
    }

    public int getОбъемЖД() {
        return объемЖД;
    }

    public void setОбъемЖД(int объемЖД) {
        this.объемЖД = объемЖД;
    }

    public String getОперационнаяСистема() {
        return операционнаяСистема;
    }

    public void setОперационнаяСистема(String операционнаяСистема) {
        this.операционнаяСистема = операционнаяСистема;
    }

    public String getЦвет() {
        return цвет;
    }

    public void setЦвет(String цвет) {
        this.цвет = цвет;
    }

    // Метод для фильтрации ноутбуков по заданным критериям
    public static Set<Ноутбук> фильтроватьНоутбуки(Set<Ноутбук> ноутбуки, Map<Integer, Object> фильтр) {
        Set<Ноутбук> результат = new HashSet<>();

        for (Ноутбук ноутбук : ноутбуки) {
            boolean соответствует = true;

            for (Map.Entry<Integer, Object> entry : фильтр.entrySet()) {
                int критерий = entry.getKey();
                Object значение = entry.getValue();

                switch (критерий) {
                    case 1:
                        int минимальныйОбъемОЗУ = (int) значение;
                        if (ноутбук.getОбъемОЗУ() < минимальныйОбъемОЗУ) {
                            соответствует = false;
                        }
                        break;
                    case 2:
                        int минимальныйОбъемЖД = (int) значение;
                        if (ноутбук.getОбъемЖД() < минимальныйОбъемЖД) {
                            соответствует = false;
                        }
                        break;
                    case 3:
                        String операционнаяСистема = (String) значение;
                        if (!ноутбук.getОперационнаяСистема().equals(операционнаяСистема)) {
                            соответствует = false;
                        }
                        break;
                    case 4:
                        String цвет = (String) значение;
                        if (!ноутбук.getЦвет().equals(цвет)) {
                            соответствует = false;
                        }
                        break;
                    // Добавьте дополнительные критерии, если необходимо
                }
            }

            if (соответствует) {
                результат.add(ноутбук);
            }
        }

        return результат;
    }

    public static void main(String[] args) {
        // Создаем множество ноутбуков
        Set<Ноутбук> ноутбуки = new HashSet<>();
        ноутбуки.add(new Ноутбук("Ноутбук1", 8, 512, "Windows", "Черный"));
        ноутбуки.add(new Ноутбук("Ноутбук2", 16, 1024, "MacOS", "Серебристый"));
        ноутбуки.add(new Ноутбук("Ноутбук3", 8, 256, "Linux", "Красный"));
        ноутбуки.add(new Ноутбук("Ноутбук4", 16, 512, "Windows", "Черный"));

        // Запрашиваем критерии фильтрации у пользователя
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> фильтр = new HashMap<>();
        System.out.println("Введите цифру, соответствующую критерию фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.print("Выберите критерий: ");
        int критерий = scanner.nextInt();
        System.out.print("Введите минимальное значение: ");
        Object значение = null;
        if (критерий == 1 || критерий == 2) {
            значение = scanner.nextInt();
        } else {
            scanner.nextLine(); // Очистка буфера
            значение = scanner.nextLine();
        }
        фильтр.put(критерий, значение);

        // Фильтруем ноутбуки и выводим результат
        Set<Ноутбук> отфильтрованныеНоутбуки = фильтроватьНоутбуки(ноутбуки, фильтр);
        for (Ноутбук ноутбук : отфильтрованныеНоутбуки) {
            System.out.println(ноутбук.getМодель());
        }
    }
}
