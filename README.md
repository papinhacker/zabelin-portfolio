# Dont readme please.
## Как это запустить? Хороший вопрос.

Для локального запуска любого теста нужно в параметры запуска прописать данные значения.
Чтобы это сделать нужно:
1. Тыкнуть на дропдаун.
2. Кликнуть на эту кнопку.
3. В параметрах конфигурации выбрать TestNG и прописать внуть параметры. О параметрах инфа чуть ниже 
![Куда тыкать](https://github.com/papinhacker/zabelin-portfolio/blob/master/src/main/resources/kuda_tickat.png)

# Параметры запуска
## Основные для локального закуска
-ea
-Dmbrowser=chrome // браузер. для хедлесс запуска 'headlesschrome'. \
-Dmenv=staging \
-DthreadCount=26 \
-Dmplatform=windows \
-Dmpropkey= // ключ дешифровки всей сенситив даты из пропертей. \
-DmpropsFile=C:/Users/Cronos/IdeaProjects/qa-automation/src/main/resources/PropertyFiles/config.yml \
## Дополнительные
-DxmlPath= // пусть к эксэмээлке, в которой прописана сьюта тестов, которые собираемся гнать. \
-Dmhost= // удалённый хост: сауслаб или селенойд, или ещё чё-нить. \
-DretryOnFailCount= // сколько раз перезапускаем упавшие тесты? \
-Dmtimeout= // переназначить стандартый таймаут для всяких вейтов. \
-Dloglevel= // можно изменить уровень логирования. \
-Dtestng.dtd.http=true // this helps to ignore using 'http' instead of 'https' in custom.xml suite header when specifying testng dtd link. \

### Стандартные мавеневские ключи, корые рекомендкуются к использованию.
-e // produce execution error messages \
-q // quiet output - only show errors 

##### Немного творчества не помешает.
[Потом напишу сюда что-нибудь умное или смешное](https://coub.com/view/33u60q)