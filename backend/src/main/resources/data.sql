-- =========================
-- FLIGHTS
-- =========================
INSERT INTO flight (
  id_flight,
  departure_city,
  departure_airport,
  arrival_city,
  arrival_airport,
  date,
  departure_time,
  arrival_time,
  company,
  price
) VALUES
(1, 'casablanca', 'CMN', 'paris', 'CDG', '2026-01-20', '10:30', '14:45', 'Air France', 1200),
(2, 'paris', 'CDG', 'casablanca', 'CMN', '2026-01-25', '16:00', '19:30', 'Air France', 1150),
(3, 'casablanca', 'CMN', 'madrid', 'MAD', '2026-01-20', '09:00', '11:30', 'Iberia', 800);

-- =========================
-- SEATS
-- =========================
INSERT INTO seats (
  id_flight,
  a1,b1,c1,d1,
  a2,b2,c2,d2,
  a3,b3,c3,d3,
  a4,b4,c4,d4,
  a5,b5,c5,d5,
  a6,b6,c6,d6,
  a7,b7,c7,d7,
  a8,b8,c8,d8,
  a9,b9,c9,d9
) VALUES
(1,
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free'
),
(2,
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free',
 'free','free','free','free'
);
