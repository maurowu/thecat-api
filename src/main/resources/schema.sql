DROP TABLE IF EXISTS CATS_BREEDS;

DROP TABLE IF EXISTS CATS_IMAGES;

CREATE TABLE IF NOT EXISTS CATS_BREEDS (
  cat_id VARCHAR(50) NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  origin VARCHAR(50) NOT NULL,
  temperament VARCHAR(250)  NULL,
  description VARCHAR(2000)  NULL
);

CREATE TABLE IF NOT EXISTS CATS_IMAGES (
  image_id VARCHAR(50) NOT NULL PRIMARY KEY,
  cat_id VARCHAR(50),
  category_id VARCHAR(2),
  image_url VARCHAR(500) NOT NULL
);