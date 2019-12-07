API
methods: getInfoUser, getEvent, writeEvent

# getInfoUser
# Parameters: access_token - token
# example:  curl --data "{\"method\" : \"getInfoUser\", \"access_token\" : \"ceff684081c750e5b6c794dd25e95c50124d56e061e76442dbb38130259b97d1c1abc69ede8626d77bda8\"}" --header "Content-Type: application/json" http://localhost:8008

# getEvent
# Parameters: category - category
# example:  curl --data "{\"method\" : \"getEvent\", \"category\" : \"It\"}" --header "Content-Type: application/json" http://localhost:8008


????? - May be works ))00)
# writeEvent
# Parameters: event
# example:  curl --data "{\"method\" : \"writeEvent\", \"event\" : \{'title': 'VSU HACKATHON', 'description': 'Первый хакатон в ВГУ, который пройдёт в рамках Local Hack Day одновременно с другими домашними хакатонами более чем в 50 странах! Здесь вас ждёт один полный выходной неограниченного творчества в решении интересных задач, полная свобода идей, ламповость, мерч и плюшки. Коктейль из активных студентов и выпускников из совершенно разных сфер деятельности выведет вас за рамки привычных занятий. Будут представлены известнейшие международные и Воронежские IT компании. Для начинающих приготовлены увлекательные воркшопы! VSU Hackathon проводится при поддержке Major League Hacking - топовой международной организации, причастной к крупнейшим хакам Европы и России - Junction и Hack.Moscow.', 'contacts': 'xcvxcv', 'place': 'Университетская пл. 1, Воронеж', 'date': None}" --header "Content-Type: application/json" http://localhost:8008

