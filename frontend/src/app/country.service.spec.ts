import { TestBed } from '@angular/core/testing';

import { CountryService } from './country.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CountryModel } from './models/country.model';

describe('CountryService', () => {
  let service: CountryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ CountryService ],
      imports: [ HttpClientTestingModule ]
    });

    service = TestBed.get(CountryService);
  });

  it('should list countries by loading them from the backend first, then from a cache', () => {
    const controller: HttpTestingController = TestBed.get(HttpTestingController);

    const expectedCountries: Array<CountryModel> = [{ id: 'BEL', name: 'Belgique' }];

    let actualCountries1: Array<CountryModel>;
    let actualCountries2: Array<CountryModel>;
    let actualCountries3: Array<CountryModel>;
    service.list().subscribe(result => actualCountries1 = result);
    service.list().subscribe(result => actualCountries2 = result);

    controller.expectOne('/api/countries').flush(expectedCountries);
    controller.verify();

    expect(actualCountries1).toEqual(expectedCountries);
    expect(actualCountries2).toEqual(expectedCountries);
    expect(actualCountries2).not.toBe(actualCountries1);

    service.list().subscribe(result => actualCountries3 = result);
    expect(actualCountries3).toEqual(expectedCountries);
  });
});