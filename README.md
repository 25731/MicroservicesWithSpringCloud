# MicroservicesWithSpringCloud

#Jest to projekt mikroserwisów, zbudowany w dużej mierze na Spring Cloud odczas projektowania systemu, Podczas projektowania starałam się unikać nieodpowiednich form sprzężenia między mikroserwisami, jak współdzieloną bazę danych, przekazują dane między sobą i modyfikują je, lub sytuację kiedy usługa ma bezpośredni dostęp do baz danych innych usług w celu zmiany ich stanu. Te trzy scenariusze zostały uniknięte, aby zapewnić spójność danych, odpowiedzialność usług za ich specyficzne funkcje, i uniknąć tworzenia nieodpowiednich zależności. Starając się skupić na przy zachowaniu modelu sprzężenia domenowego (Domain Coupling). Sprzężenie domenowe jest nieuniknione w systemach mikroserwisów, gdyż usługi muszą korzystać z danych udostępnianych przez inne usługi, ale na pewno nie mogą zmieniać ich stanu bazy danych innej usługi
 
#Zastosowałem model reaktywny, nieblokujący który pozwala na efektywną komunikację między mikroserwisami. Dzięki niemu, nawet wywołania metod z innych mikroserwisów nie blokują bieżących operacji, co przekłada się na lepszą wydajność i odporność na błędy.

#Kolejnym elementem architektury jest klaster Eureka, który umożliwia replikację usług odkrywania. To rozwiązanie zapewnia redundancję w przypadku awarii jednej z instancji Eureki, co przekłada się na większą niezawodność systemu.

#Projekt działa w dwóch trybach: 'native' i 'test'. Tryb 'native' korzysta z lokalnej bazy danych Postgres. Aby aplikacja mogła współpracować z tą bazą, należy ją zainstalować lokalnie

#Zarządzanie konfiguracją odbywa się za pomocą Spring Cloud Config Server, który pobiera ustawienia dla różnych profili z plików lokalnych. Aby zmienić tryb działania aplikacji, należy w każdym pliku 'application.properties' ustawić odpowiednią linijkę - 'test' lub 'native'.

#Komunikacja między usługami realizowana jest nie tylko za pomocą nieblokującego API, ale także asynchronicznie, za pomocą brokera RabbitMQ a także Spring Cloud Stream. Aby projekt w pełni działał, RabbitMQ musi być zainstalowany lokalnie, aby umożliwić publikowanie i konsumpcję wiadomości.

#Każda usługa dostarcza punkty końcowe dla systemu monitorującego Prometheus, a wizualizacje danych są generowane przez Grafana. Aby skorzystać z tej funkcji, również należy Grafanę zainstalować lokalnie lub hostować w chmurze, a źródło danych podać jako localhost:9090 na którym jest Prometheus.

#Routing w systemie jest realizowany manualnie, jednak w przypadku większej ilości mikroserwisów i potrzeby skalowania, można skorzystać z automatycznego routingu za pomocą Eureki.

