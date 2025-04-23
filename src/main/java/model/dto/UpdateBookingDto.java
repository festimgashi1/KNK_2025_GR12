package model.dto;


    import java.time.LocalDateTime;

    public class UpdateBookingDto {
        private LocalDateTime dataENisjes;
        private String destinacioni;
        private Double cmimi;



        public UpdateBookingDto(LocalDateTime dataENisjes, String destinacioni, Double cmimi) {
            this.dataENisjes = dataENisjes;
            this.destinacioni = destinacioni;
            this.cmimi = cmimi;
        }

        public LocalDateTime getDataENisjes() {
            return dataENisjes;
        }

        public void setDataENisjes(LocalDateTime dataENisjes) {
            this.dataENisjes = dataENisjes;
        }

        public String getDestinacioni() {
            return destinacioni;
        }

        public void setDestinacioni(String destinacioni) {
            this.destinacioni = destinacioni;
        }

        public Double getCmimi() {
            return cmimi;
        }

        public void setCmimi(Double cmimi) {
            this.cmimi = cmimi;
        }
    }


