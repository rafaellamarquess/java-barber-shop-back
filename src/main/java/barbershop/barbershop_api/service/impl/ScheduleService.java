package barbershop.barbershop_api.service.impl;


import barbershop.barbershop_api.entity.ScheduleEntity;
import barbershop.barbershop_api.repository.IScheduleRepository;
import barbershop.barbershop_api.service.IScheduleService;
import barbershop.barbershop_api.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return repository.save(entity);
    }

    @Override
    public void delete(final long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
