import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BussinessSignUpFormComponent } from './bussiness-sign-up-form.component';

describe('BussinessSignUpFormComponent', () => {
  let component: BussinessSignUpFormComponent;
  let fixture: ComponentFixture<BussinessSignUpFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BussinessSignUpFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BussinessSignUpFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
