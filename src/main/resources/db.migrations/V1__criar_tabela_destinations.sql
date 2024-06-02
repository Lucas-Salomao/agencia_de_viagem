CREATE TABLE public.destinations (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              location VARCHAR(255) NOT NULL,
                              description TEXT NOT NULL,
                              rating DECIMAL(3,1) NOT NULL
);