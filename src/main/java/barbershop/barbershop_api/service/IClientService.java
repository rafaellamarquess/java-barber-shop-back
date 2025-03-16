package barbershop.barbershop_api.service;

public interface IClientService {

    ClientEntity save(final ClientEntity entity);

    ClientEntity update(final ClientEntity entity);

    void delete(final long id);

}
