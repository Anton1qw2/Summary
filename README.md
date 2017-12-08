# Резюме
Выполненные мной задачи

В папке Delta8 находитcя проект с Intel Delta Course 8. А именно, запуск нейросети для генерации лиц. Входом даной сети является массив размерностью в 64 числа с плавующей точкой а выходом является трех канальное RGB изображение размером 64x64px.
Так же в процессе изучения этой сети я обнаружил тот факт, что похожие лица находятся рядом. Скрипт FaceGane.py запускает нейронную
сеть и выводит в окнах 2 различных лица. Затем записывает видео из 100 кадров где одно лицо перетекает в другое. В данном проекте использовались знания OpenCV и машинного обучения.
Так же проект был выполнен на Python.


---


Папка MediaSDK содержит проект на С++ который используя библиотеку libjpeg сжимает выбранное изображение и записывает его в в файл

Пример запуска программы

```
/home/anton/MediaSDK/res/input.jpg  /home/anton/MediaSDK/res/output.jpg 80
```

где /home/anton/MediaSDK/res/input.jpg - входное изображение

home/anton/MediaSDK/res/output.jpg - выходное изображение

80 - качество сжатия в диапозоне от 1 до 100 (чем больше тем лучше)

---

Папка Java_Oracle_Project cодержит проект написанный на Java.

Данная программа представляет собой консольное приложение предоставляющее доступ к базе данных. Данный проект демонстрирует навыки работы с ООП. Суть проекта заключается в работе с базой данных Oracle при помощи клиента на Java. Клиент может сгенерировать "разумную" (все связи будут логичны и ни один объект не будет ссылаться на несуществующий id), и получать различную информацию из таблиц. Проект написан с использованием технологии Spring и использованием драйвера JDBC для доступа к БД.


---

Так же мной был напиан [туториал по установке caffe](https://github.com/Anton1qw2/IntelDelta8/wiki/Установка-caffe-на-Ubuntu-16.04-LTS)

